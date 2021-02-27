package com.usacheow.coreui.delegate

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.usacheow.coreui.R
import com.usacheow.coreui.base.BackListener

class ContainerDelegate(
    private val initFragmentTag: String
) {

    fun onCreate(fragmentManager: FragmentManager, getInitFragment: () -> Fragment) {
        if (fragmentManager.fragments.isEmpty()) {
            fragmentManager.commit {
                replace(R.id.fragmentContainer, getInitFragment(), initFragmentTag)
            }
        }
    }

    fun showFragment(fragmentManager: FragmentManager, fragment: Fragment, needAddToBackStack: Boolean, needAnimate: Boolean) {
        fragmentManager.commit {
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
        }
    }

    fun resetContainer(fragmentManager: FragmentManager) {
        while (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStackImmediate()
        }
    }

    fun closeContainer(fragmentManager: FragmentManager) {
        fragmentManager.popBackStackImmediate()
    }

    fun onBackPressed(fragmentManager: FragmentManager): Boolean {
        val activeFragment = fragmentManager.findFragmentById(R.id.fragmentContainer)
        val backStackEntryCount = fragmentManager.backStackEntryCount

        return if (activeFragment is BackListener && activeFragment.onBackPressed()) {
            true
        } else if (activeFragment != null && backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }
}