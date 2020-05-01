package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.usacheow.coreuikit.fragments.ContainerFragment

class AuthContainerFragment : ContainerFragment() {

    override val INIT_FRAGMENT_TAG_KEY = "HelloContainerFragment"

    companion object {
        const val REQUEST_KEY = "AuthContainerFragment"
        const val IS_AUTH_SUCCESS = "IS_AUTH_SUCCESS"

        fun newInstance() = AuthContainerFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener(SignInWithPhoneFragment.REQUEST_KEY, this) { _, bundle ->
            val isSuccess = bundle.getBoolean(SignInWithPhoneFragment.IS_SUCCESS)
            setFragmentResult(REQUEST_KEY, bundleOf(IS_AUTH_SUCCESS to isSuccess))
        }

        childFragmentManager.setFragmentResultListener(SignInFragment.REQUEST_KEY, this) { _, bundle ->
            val isSuccess = bundle.getBoolean(SignInFragment.IS_SUCCESS)
            setFragmentResult(REQUEST_KEY, bundleOf(IS_AUTH_SUCCESS to isSuccess))
        }

        childFragmentManager.setFragmentResultListener(SignUpFragment.REQUEST_KEY, this) { _, bundle ->
            val isSuccess = bundle.getBoolean(SignUpFragment.IS_SUCCESS)
            setFragmentResult(REQUEST_KEY, bundleOf(IS_AUTH_SUCCESS to isSuccess))
        }
    }

    override fun getInitFragment() = SignInFragment.newInstance()
}