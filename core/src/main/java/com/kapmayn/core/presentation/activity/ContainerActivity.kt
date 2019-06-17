package com.kapmayn.core.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kapmayn.core.R
import com.kapmayn.core.presentation.base.IContainer
import com.kapmayn.core.presentation.fragments.SimpleFragment
import com.kapmayn.core.utils.inTransaction
import com.kapmayn.core.utils.replaceFragmentIn

abstract class ContainerActivity : SimpleActivity(), IContainer {

    override val layoutId = R.layout.frg_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.replaceFragmentIn(R.id.fragmentContainer, getStartFragment(), true)
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

        supportFragmentManager.inTransaction {
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

    protected abstract fun getStartFragment(): Fragment

    override fun reset() {
        while (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onBackPressed() {
        val activeFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val backStackEntryCount = supportFragmentManager.backStackEntryCount

        if (activeFragment is SimpleFragment && activeFragment.onBackPressed()) {
        } else if (activeFragment != null && backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }

    override fun onBackClicked() = false
}