package com.usacheow.featureauth.presentation.router

import androidx.fragment.app.Fragment
import com.usacheow.appshared.otp.SmsCodeModalFragment
import com.usacheow.coreui.base.Router
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
import javax.inject.Inject

class AuthorizationRouter
@Inject constructor(fragment: Fragment) : Router(fragment) {

    fun openSignUpScreen() = simpleFragment?.getContainer {
        navigateTo(SignUpFragment.newInstance())
    }

    fun openConfirmScreen(codeLength: Int) {
        SmsCodeModalFragment.newInstance(codeLength)
            .show(fragment.childFragmentManager, SmsCodeModalFragment::javaClass.name)
    }
}