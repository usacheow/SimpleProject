package com.usacheow.featurehello.presentation.fragment

import com.usacheow.coreuikit.fragments.ContainerFragment

class HelloContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = HelloContainerFragment()
    }

    override fun getInitFragment() = AFragment.newInstance()
}