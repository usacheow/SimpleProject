package com.usacheow.featureauth.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavDirections
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addNextScreenDirection
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureauth.R
import javax.inject.Inject

class AuthorizationMediatorImpl @Inject constructor() : AuthorizationMediator {

    override fun getPinCodeFlowDirection(
        nextScreenDirection: NavDirections,
    ) = screen(R.id.pin_code_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignInFlowDirection(
        nextScreenDirection: NavDirections,
    ) = screen(R.id.sign_in_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignInWithPhoneFlowDirection(
        nextScreenDirection: NavDirections,
    ) = screen(R.id.sign_in_with_phone_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)

    override fun getSignUpFlowDirection(
        nextScreenDirection: NavDirections,
    ) = screen(R.id.sign_up_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)
}