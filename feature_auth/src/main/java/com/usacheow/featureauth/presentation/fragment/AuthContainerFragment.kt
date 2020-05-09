package com.usacheow.featureauth.presentation.fragment

import com.usacheow.coreuikit.fragments.ContainerFragment

class AuthContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = AuthContainerFragment()
    }

    override fun getInitFragment() = SignInFragment.newInstance()
}