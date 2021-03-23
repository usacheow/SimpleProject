package com.usacheow.featureauth.presentation.fragment

import com.usacheow.coreui.fragment.ContainerFragment

class AuthContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = AuthContainerFragment()
    }

    override fun getInitFragment() = SignInWithPhoneFragment.newInstance()
}