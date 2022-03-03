package com.usacheow.featureauth.presentation.navigation

import android.os.Bundle
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.corenavigation.AuthorizationFeatureProvider
import com.usacheow.corenavigation.base.WITH
import com.usacheow.corenavigation.base.addNextScreenDirection
import com.usacheow.corenavigation.base.screen
import javax.inject.Inject
import com.usacheow.featureauth.R as FeatureR

class AuthorizationFeatureProviderImpl @Inject constructor() : AuthorizationFeatureProvider {

    override fun getPinCodeFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.pin_code_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignInFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.sign_in_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignInWithPhoneFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.sign_in_with_phone_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignUpFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.sign_up_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)
}