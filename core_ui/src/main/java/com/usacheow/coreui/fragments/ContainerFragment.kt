package com.usacheow.coreui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import androidx.viewbinding.ViewBinding
import com.usacheow.coreui.R
import com.usacheow.coreui.base.IBackListener
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate

abstract class ContainerFragment : SimpleFragment<FragmentContainerBinding>(), IContainer, IBackListener {

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

    protected abstract fun getInitFragment(): Fragment

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentContainerBinding {
        return FragmentContainerBinding.inflate(inflater, container, false)
    }

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