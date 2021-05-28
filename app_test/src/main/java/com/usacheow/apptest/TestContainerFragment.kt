package com.usacheow.apptest

import com.usacheow.coreui.fragment.ContainerFragment

class TestContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = TestContainerFragment()
    }

//    override fun getInitFragment() = NotificationsFragment.newInstance()
    override fun getInitFragment() = CoroutinesFragment.newInstance()
}