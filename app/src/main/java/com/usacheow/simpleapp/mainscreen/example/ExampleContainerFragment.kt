package com.usacheow.simpleapp.mainscreen.example

import com.usacheow.coreuikit.fragments.ContainerFragment

class ExampleContainerFragment : ContainerFragment() {

    override val INIT_FRAGMENT_TAG_KEY = ExampleContainerFragment::javaClass.name

    companion object {
        fun newInstance() = ExampleContainerFragment()
    }

    override fun getInitFragment() = ExampleFragment.newInstance()
}