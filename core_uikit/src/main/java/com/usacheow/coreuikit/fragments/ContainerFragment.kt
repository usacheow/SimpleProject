package com.usacheow.coreuikit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.delegate.ContainerDelegate

abstract class ContainerFragment : SimpleFragment(), IContainer, IBackListener {

    override val layoutId = R.layout.frg_container

    private val containerDelegate by lazy { ContainerDelegate() }

    protected abstract fun getInitFragment(): Fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        containerDelegate.onCreate(childFragmentManager, ::getInitFragment)
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, transition: TransitionSet) {
        containerDelegate.show(childFragmentManager, fragment, needAddToBackStack, transition)
    }

    override fun reset() {
        containerDelegate.reset(childFragmentManager)
    }

    override fun onBackPressed(): Boolean {
        return containerDelegate.onBackPressed(childFragmentManager)
    }
}