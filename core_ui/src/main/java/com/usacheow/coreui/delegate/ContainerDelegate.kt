package com.usacheow.coreui.delegate

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.TransitionSet
import com.usacheow.coreui.R
import com.usacheow.coreui.base.IBackListener
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.navigation.addSharedElementsFrom
import com.usacheow.coreui.utils.navigation.inTransaction
import com.usacheow.coreui.utils.navigation.replaceFragmentIn
import com.usacheow.coreui.utils.system.ifSupportLollipop

class ContainerDelegate(
    private val initFragmentTag: String
) {

    fun onCreate(fragmentManager: FragmentManager, getInitFragment: () -> Fragment) {
//        if (fragmentManager.findFragmentByTag(INIT_FRAGMENT_HASH_TAG) == null) {
        if (fragmentManager.fragments.isEmpty()) {
            fragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), false, initFragmentTag)
        }
    }

    fun show(fragmentManager: FragmentManager, fragment: Fragment, needAddToBackStack: Boolean, needAnimate: Boolean) {
//        val activeFragment = fragmentManager.findFragmentById(R.id.fragmentContainer)

        fragmentManager.inTransaction {
//            ifSupportLollipop {
//                fragment.sharedElementEnterTransition = transition
//                fragment.sharedElementReturnTransition = transition
//            }
//            addSharedElementsFrom(activeFragment as? SimpleFragment)
            if (needAnimate) {
                setCustomAnimations(
                    R.anim.anim_enter_from_right,
                    R.anim.anim_exit_to_left,
                    R.anim.anim_enter_from_left,
                    R.anim.anim_exit_to_right
                )
            }
            replace(R.id.fragmentContainer, fragment)
            if (needAddToBackStack) addToBackStack(null)
            this
        }
    }

    fun reset(fragmentManager: FragmentManager) {
        while (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStackImmediate()
        }
    }

    fun onBackPressed(fragmentManager: FragmentManager): Boolean {
        val activeFragment = fragmentManager.findFragmentById(R.id.fragmentContainer)
        val backStackEntryCount = fragmentManager.backStackEntryCount

        return if (activeFragment is IBackListener && activeFragment.onBackPressed()) {
            true
        } else if (activeFragment != null && backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }
}