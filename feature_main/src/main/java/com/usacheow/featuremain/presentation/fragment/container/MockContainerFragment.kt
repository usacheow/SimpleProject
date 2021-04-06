package com.usacheow.featuremain.presentation.fragment.container

import com.usacheow.coreui.fragment.ContainerFragment
import com.usacheow.featuremain.presentation.fragment.MockFragment

class MockContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = MockContainerFragment()
    }

    override fun getInitFragment() = MockFragment.newInstance()
}