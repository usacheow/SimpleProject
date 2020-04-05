package com.usacheow.featurehello.presentation.fragment

import com.usacheow.coreuikit.fragments.ContainerFragment

class TestContainerFragment : ContainerFragment() {

    override val INIT_FRAGMENT_TAG_KEY = TestContainerFragment::javaClass.name

    companion object {
        fun newInstance() = TestContainerFragment()
    }

    override fun getInitFragment() = AFragment()
}