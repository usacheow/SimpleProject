package com.usacheow.featureauth.presentation.router

import androidx.lifecycle.ViewModelProvider
import com.usacheow.coreuikit.base.Router
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
import com.usacheow.otp.SmsCodeModalFragment
import javax.inject.Inject

class AuthorizationRouter
@Inject constructor() : Router {

    fun openSignUpScreen(fragment: SimpleFragment) {
        fragment.getContainer {
            show(SignUpFragment.newInstance())
        }
    }

    fun openConfirmScreen(fragment: SimpleFragment, codeLength: Int, viewModelFactory: ViewModelProvider.Factory) {
        SmsCodeModalFragment.newInstance(codeLength)
            .also { it.viewModelFactory = viewModelFactory }
            .show(fragment.childFragmentManager, SmsCodeModalFragment::javaClass.name)
    }
}