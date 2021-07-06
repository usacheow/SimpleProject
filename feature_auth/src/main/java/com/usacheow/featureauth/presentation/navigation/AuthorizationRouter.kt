package com.usacheow.featureauth.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.base.Router
import com.usacheow.featureauth.R
import javax.inject.Inject

class AuthorizationRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationMediator: AuthorizationMediator,
    private val otpMediator: OtpMediator,
) : Router(fragment) {

    fun openSignUpScreen() {
        navigateTo(authorizationMediator.getSignUpFlowDirection())
    }

    fun openSmsCodeScreen(codeLength: Int) {
        navigateTo(otpMediator.getOtpFlowDirection(codeLength))
    }
}