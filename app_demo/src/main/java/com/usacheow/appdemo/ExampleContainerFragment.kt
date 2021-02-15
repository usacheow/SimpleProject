package com.usacheow.appdemo

import com.usacheow.coreui.fragments.ContainerFragment

class ExampleContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = ExampleContainerFragment()
    }

    override fun getInitFragment() = ExampleFragment.newInstance()
}