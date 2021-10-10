package com.usacheow.featureauth.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.base.Router
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coreui.utils.navigation.openIn
import javax.inject.Inject

class AuthorizationRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationMediator: AuthorizationMediator,
    private val otpMediator: OtpMediator,
) : Router(fragment) {

    fun toSignUpFlow(nextScreenDirection: FeatureNavDirection) {
        authorizationMediator.getSignUpFlowDirection(nextScreenDirection).openIn(navController)
    }

    fun toSmsCodeFlow(codeLength: Int) {
        otpMediator.getOtpFlowDirection(OtpMediator.OtpArgs(codeLength)).openIn(navController)
    }
}