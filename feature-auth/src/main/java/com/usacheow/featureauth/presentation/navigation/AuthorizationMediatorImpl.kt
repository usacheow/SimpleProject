package com.usacheow.featureauth.presentation.navigation

import android.os.Bundle
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addNextScreenDirection
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureauth.R as FeatureR
import javax.inject.Inject

class AuthorizationMediatorImpl @Inject constructor() : AuthorizationMediator {

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