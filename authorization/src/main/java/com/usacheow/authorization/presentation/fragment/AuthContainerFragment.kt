package com.usacheow.authorization.presentation.fragment

import com.usacheow.coreuikit.fragments.ContainerFragment

class AuthContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = AuthContainerFragment()
    }

    override val INIT_FRAGMENT_TAG_KEY = AuthContainerFragment::javaClass.name

    override fun getInitFragment() = SignInByPhoneFragment.newInstance()
}