package com.usacheow.featureauth.presentation.navigation

import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.featureauth.R
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureauth.presentation.fragment.SignInFragment
import com.usacheow.featureauth.presentation.fragment.SignInWithPhoneFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
import javax.inject.Inject

class AuthorizationMediatorImpl @Inject constructor() : AuthorizationMediator {

    override fun getPinCodeFlowDirection() = FeatureNavDirection(R.id.pin_code_nav_graph)

    override fun getSignInFlowDirection() = FeatureNavDirection(R.id.sign_in_nav_graph)

    override fun getSignInWithPhoneFlowDirection() = FeatureNavDirection(R.id.sign_in_with_phone_nav_graph)

    override fun getSignUpFlowDirection() = FeatureNavDirection(R.id.sign_up_nav_graph)
}