package com.usacheow.coreuikit.delegate

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.addSharedElementsFrom
import com.usacheow.coreuikit.utils.ext.inTransaction
import com.usacheow.coreuikit.utils.ext.replaceFragmentIn
import com.usacheow.coreuikit.utils.ifSupportLollipop

private const val INIT_FRAGMENT_HASH_TAG: String = "CONTAINER_TAG"

class ContainerDelegate {

    val layoutId = R.layout.frg_container

    fun onCreate(fragmentManager: FragmentManager, getInitFragment: () -> Fragment) {
        if (fragmentManager.findFragmentByTag(INIT_FRAGMENT_HASH_TAG) == null) {
            fragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), true, INIT_FRAGMENT_HASH_TAG)
        }
    }

    fun show(fragmentManager: FragmentManager, fragment: Fragment, needAddToBackStack: Boolean, transition: TransitionSet) {
        val activeFragment = fragmentManager.findFragmentById(R.id.fragmentContainer)

        fragmentManager.inTransaction {
            ifSupportLollipop {
                fragment.sharedElementEnterTransition = transition
                fragment.sharedElementReturnTransition = transition
            }
            addSharedElementsFrom(activeFragment as? SimpleFragment)
//            setCustomAnimations(
//                R.anim.anim_enter_from_right,
//                R.anim.anim_exit_to_left,
//                R.anim.anim_enter_from_left,
//                R.anim.anim_exit_to_right
//            )
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
        } else if (activeFragment != null && backStackEntryCount > 1) {
            fragmentManager.popBackStackImmediate()
            true
        } else {
            false
        }
    }
}