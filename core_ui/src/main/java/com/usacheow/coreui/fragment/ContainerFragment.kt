package com.usacheow.coreui.fragment

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        containerDelegate.showInitFragment(childFragmentManager, ::getInitFragment)
    }

    override fun navigateTo(
        fragment: Fragment,
        needAddToBackStack: Boolean,
        needAnimate: Boolean,
        needReplace: Boolean,
    ) {
        containerDelegate.navigateTo(childFragmentManager, fragment, needAddToBackStack, needAnimate, needReplace)
    }

    override fun resetContainer() {
        containerDelegate.resetContainer(childFragmentManager)
    }

    override fun closeContainer() {
        containerDelegate.closeContainer(parentFragmentManager)
    }

    override fun onBackPressed(): Boolean {
        return containerDelegate.onBackPressed(childFragmentManager)
    }
}