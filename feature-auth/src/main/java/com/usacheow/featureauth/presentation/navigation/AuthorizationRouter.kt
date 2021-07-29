package com.usacheow.featureauth.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.base.Router
import javax.inject.Inject

class AuthorizationRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationMediator: AuthorizationMediator,
    private val otpMediator: OtpMediator,
) : Router(fragment) {

    fun toSignUpFlow() {
        navigateTo(authorizationMediator.getSignUpFlowDirection())
    }

    fun toSmsCodeFlow(codeLength: Int) {
        navigateTo(otpMediator.getOtpFlowDirection(codeLength))
    }
}