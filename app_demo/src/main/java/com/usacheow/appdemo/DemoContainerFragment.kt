package com.usacheow.appdemo

import com.usacheow.coreui.fragments.ContainerFragment

class DemoContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = DemoContainerFragment()
    }

    override fun getInitFragment() = DemoFragment.newInstance()
}