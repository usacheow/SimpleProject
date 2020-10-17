package com.usacheow.coreui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreui.R
import com.usacheow.coreui.base.IBackListener
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.delegate.ContainerDelegate

abstract class ContainerFragment : SimpleFragment(), IContainer, IBackListener {

    override val layoutId = R.layout.fragment_container

    private val containerDelegate by lazy { ContainerDelegate(layoutId) }

    protected abstract fun getInitFragment(): Fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        containerDelegate.onCreate(childFragmentManager, ::getInitFragment)
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, needAnimate: Boolean) {
        containerDelegate.show(childFragmentManager, fragment, needAddToBackStack, needAnimate)
    }

    override fun reset() {
        containerDelegate.reset(childFragmentManager)
    }

    override fun onBackPressed(): Boolean {
        return containerDelegate.onBackPressed(childFragmentManager)
    }
}