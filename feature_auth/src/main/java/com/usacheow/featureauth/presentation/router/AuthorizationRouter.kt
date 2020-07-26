package com.usacheow.featureauth.presentation.router

import com.usacheow.app_shared.otp.SmsCodeModalFragment
import com.usacheow.coreui.base.Router
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
import javax.inject.Inject

class AuthorizationRouter
@Inject constructor() : Router {

    fun openSignUpScreen(fragment: SimpleFragment) {
        fragment.getContainer {
            show(SignUpFragment.newInstance())
        }
    }

    fun openConfirmScreen(fragment: SimpleFragment, codeLength: Int) {
        SmsCodeModalFragment.newInstance(codeLength)
            .show(fragment.childFragmentManager, SmsCodeModalFragment::javaClass.name)
    }
}