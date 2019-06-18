package com.kapmayn.core.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kapmayn.core.R
import com.kapmayn.core.presentation.base.IContainer
import com.kapmayn.core.utils.inTransaction
import com.kapmayn.core.utils.replaceFragmentIn

abstract class ContainerFragment : Fragment(), IContainer {

    abstract val INIT_FRAGMENT_TAG_KEY: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentByTag(INIT_FRAGMENT_TAG_KEY) == null) {
            childFragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), true, INIT_FRAGMENT_TAG_KEY)
        }
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean) {
//        val activeFragment = childFragmentManager.findFragmentById(R.id.fragmentContainer)
//        val transaction = childFragmentManager.createReplaceTransactionIn(R.id.fragmentContainer, fragment, needAddToBackStack)
//        if(activeFragment is SimpleFragment) {
//            fragment.sharedElementEnterTransition = TransitionInflater.from(context)
//                .inflateTransition(R.transition.transition_default_screen)
//            transaction.addSharedElements(*activeFragment.getSharedViews().toTypedArray())
//        }
//        transaction.commit()

        childFragmentManager.inTransaction {
            setCustomAnimations(
                R.anim.anim_enter_from_right,
                R.anim.anim_exit_to_left,
                R.anim.anim_enter_from_left,
                R.anim.anim_exit_to_right
            )
            replace(R.id.fragmentContainer, fragment)
            if (needAddToBackStack) addToBackStack(null)
            this
        }
    }

    protected abstract fun getInitFragment(): Fragment

    override fun reset() {
        while (childFragmentManager.backStackEntryCount > 1) {
            childFragmentManager.popBackStackImmediate()
        }
    }

    override fun onBackClicked(): Boolean {
        val activeFragment = childFragmentManager.findFragmentById(R.id.fragmentContainer)
        val backStackEntryCount = childFragmentManager.backStackEntryCount

        return if (activeFragment is SimpleFragment && activeFragment.onBackPressed()) {
            true
        } else if (activeFragment != null && backStackEntryCount > 1) {
            childFragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }
}