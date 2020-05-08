package com.usacheow.coreuikit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.utils.ext.addSharedElementsFrom
import com.usacheow.coreuikit.utils.ext.hashTag
import com.usacheow.coreuikit.utils.ext.inTransaction
import com.usacheow.coreuikit.utils.ext.replaceFragmentIn
import com.usacheow.coreuikit.utils.ifSupportLollipop

abstract class ContainerFragment : Fragment(), IContainer, IBackListener {

    private var initFragmentHashTag: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (initFragmentHashTag == null || childFragmentManager.findFragmentByTag(initFragmentHashTag) == null) {
            val fragment = getInitFragment()
            initFragmentHashTag = fragment.hashTag()
            childFragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), true, initFragmentHashTag!!)
        }
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, transition: TransitionSet) {
        val activeFragment = childFragmentManager.findFragmentById(R.id.fragmentContainer)

        childFragmentManager.inTransaction {
            //            setCustomAnimations(
//                R.anim.anim_enter_from_right,
//                R.anim.anim_exit_to_left,
//                R.anim.anim_enter_from_left,
//                R.anim.anim_exit_to_right
//            )
            ifSupportLollipop {
                fragment.sharedElementEnterTransition = transition
                fragment.sharedElementReturnTransition = transition
            }
            addSharedElementsFrom(activeFragment as? SimpleFragment)
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

    override fun onBackPressed(): Boolean {
        val activeFragment = childFragmentManager.findFragmentById(R.id.fragmentContainer)
        val backStackEntryCount = childFragmentManager.backStackEntryCount

        return if (activeFragment is IBackListener && activeFragment.onBackPressed()) {
            true
        } else if (activeFragment != null && backStackEntryCount > 1) {
            childFragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }
}