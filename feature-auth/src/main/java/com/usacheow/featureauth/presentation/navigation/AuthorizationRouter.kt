package com.usacheow.featureauth.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.corenavigation.AuthorizationFeatureProvider
import com.usacheow.corenavigation.OtpFeatureProvider
import com.usacheow.corenavigation.base.Router
import com.usacheow.corenavigation.base.OPEN_IN
import javax.inject.Inject

class AuthorizationRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationFeatureProvider: AuthorizationFeatureProvider,
    private val otpFeatureProvider: OtpFeatureProvider,
) : Router(fragment) {

    fun toSignUpFlow(nextScreenDirection: FeatureNavDirection) {
        authorizationFeatureProvider.getSignUpFlowDirection(nextScreenDirection) OPEN_IN navController
    }

    fun toSmsCodeFlow(codeLength: Int) {
        otpFeatureProvider.getOtpFlowDirection(OtpFeatureProvider.OtpArgs(codeLength)) OPEN_IN navController
    }
}