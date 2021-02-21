package com.usacheow.coreui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coreui.base.BackListener
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate

abstract class ContainerFragment : SimpleFragment<FragmentContainerBinding>(), Container, BackListener {

    override val params = Params(
        viewBindingProvider = FragmentContainerBinding::inflate,
    )

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

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