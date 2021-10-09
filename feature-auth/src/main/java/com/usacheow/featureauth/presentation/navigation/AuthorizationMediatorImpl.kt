package com.usacheow.featureauth.presentation.navigation

import android.os.Bundle
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addNextScreenDirection
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureauth.R
import javax.inject.Inject

class AuthorizationMediatorImpl @Inject constructor() : AuthorizationMediator {

    override fun getPinCodeFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(R.id.pin_code_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignInFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(R.id.sign_in_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignInWithPhoneFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(R.id.sign_in_with_phone_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignUpFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(R.id.sign_up_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)
}