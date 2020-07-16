package com.usacheow.featureauth.presentation.router

import com.usacheow.coremediator.mediator.AuthorizationMediator
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import javax.inject.Inject

class AuthorizationMediatorImpl
@Inject constructor() : AuthorizationMediator {

    override fun getAuthContainerFragment() = AuthContainerFragment.newInstance()

    override fun getPinCodeFragment() = PinCodeFragment.newInstance()
}