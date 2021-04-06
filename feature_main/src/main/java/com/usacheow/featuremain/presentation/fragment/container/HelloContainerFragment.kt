package com.usacheow.featuremain.presentation.fragment.container

import com.usacheow.coreui.fragment.ContainerFragment
import com.usacheow.featuremain.presentation.fragment.AFragment

class HelloContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = HelloContainerFragment()
    }

    override fun getInitFragment() = AFragment.newInstance()
}