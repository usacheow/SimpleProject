package com.usacheow.featurehello.presentation.fragment

import com.usacheow.coreuikit.fragments.ContainerFragment

class HelloContainerFragment : ContainerFragment() {

    override val INIT_FRAGMENT_TAG_KEY = "HelloContainerFragment"

    companion object {
        fun newInstance() = HelloContainerFragment()
    }

    override fun getInitFragment() = AFragment.newInstance()
}