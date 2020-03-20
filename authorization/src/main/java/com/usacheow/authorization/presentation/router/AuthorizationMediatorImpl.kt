package com.usacheow.authorization.presentation.router

import com.usacheow.authorization.presentation.fragment.AuthContainerFragment
import com.usacheow.authorization.presentation.fragment.PinCodeFragment
import com.usacheow.core.mediator.AuthorizationMediator
import javax.inject.Inject

class AuthorizationMediatorImpl
@Inject constructor() : AuthorizationMediator {

    override fun getAuthContainer() = AuthContainerFragment.newInstance()

    override fun getPinCodeFragment() = PinCodeFragment.newInstance()
}