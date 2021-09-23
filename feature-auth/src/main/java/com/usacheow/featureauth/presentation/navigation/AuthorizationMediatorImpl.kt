package com.usacheow.featureauth.presentation.navigation

import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureauth.R
import javax.inject.Inject

class AuthorizationMediatorImpl @Inject constructor() : AuthorizationMediator {

    override fun getPinCodeFlowDirection() = screen(R.id.pin_code_nav_graph)

    override fun getSignInFlowDirection() = screen(R.id.sign_in_nav_graph)

    override fun getSignInWithPhoneFlowDirection() = screen(R.id.sign_in_with_phone_nav_graph)

    override fun getSignUpFlowDirection() = screen(R.id.sign_up_nav_graph)
}