package com.usacheow.featureauth.presentation.navigation

import androidx.fragment.app.Fragment
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.corenavigation.AuthorizationFeatureProvider
import com.usacheow.corenavigation.base.OPEN_IN
import com.usacheow.corenavigation.base.Router
import javax.inject.Inject

class AuthorizationRouter @Inject constructor(
    fragment: Fragment,
    private val authorizationFeatureProvider: AuthorizationFeatureProvider,
) : Router(fragment) {

    fun toSignUpFlow(nextScreenDirection: FeatureNavDirection) {
        authorizationFeatureProvider.getSignUpFlowDirection(nextScreenDirection) OPEN_IN navController
    }
}