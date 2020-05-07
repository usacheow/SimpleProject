package com.usacheow.simpleapp.example

import android.os.Bundle
import androidx.fragment.app.setFragmentResultListener
import com.usacheow.coreuikit.fragments.ContainerFragment
import com.usacheow.featureauth.presentation.fragment.SignInFragment
import com.usacheow.featureauth.presentation.fragment.SignInWithPhoneFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment

class ExampleContainerFragment : ContainerFragment() {

    companion object {
        fun newInstance() = ExampleContainerFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.setFragmentResultListener(SignInWithPhoneFragment.REQUEST_KEY, this) { _, bundle ->
            onBackPressed()
        }

        childFragmentManager.setFragmentResultListener(SignInFragment.REQUEST_KEY, this) { _, bundle ->
            onBackPressed()
        }

        childFragmentManager.setFragmentResultListener(SignUpFragment.REQUEST_KEY, this) { _, bundle ->
            onBackPressed()
        }
    }

    override fun getInitFragment() = ExampleFragment.newInstance()
}