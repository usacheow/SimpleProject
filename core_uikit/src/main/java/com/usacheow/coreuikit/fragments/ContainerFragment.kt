package com.usacheow.coreuikit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.utils.ext.addSharedElementsFrom
import com.usacheow.coreuikit.utils.ext.inTransaction
import com.usacheow.coreuikit.utils.ext.replaceFragmentIn
import com.usacheow.coreuikit.utils.ifSupportLollipop
import com.usacheow.diprovider.DiProvider

abstract class ContainerFragment : SimpleFragment(), IContainer, IBackListener {

    override val layoutId = R.layout.frg_container

    private var initFragmentHashTag: String = "CONTAINER_TAG"

    protected abstract fun getInitFragment(): Fragment

    override fun inject(diProvider: DiProvider) = Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentByTag(initFragmentHashTag) == null) {
            childFragmentManager.replaceFragmentIn(R.id.fragmentContainer, getInitFragment(), true, initFragmentHashTag)
        }
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, transition: TransitionSet) {
        val activeFragment = childFragmentManager.findFragmentById(R.id.fragmentContainer)

        childFragmentManager.inTransaction {
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