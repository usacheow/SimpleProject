package com.usacheow.coremediator.mediator

import androidx.fragment.app.Fragment

interface AuthorizationMediator {

    fun getAuthContainerFragment(): Fragment

    fun getPinCodeFragment(): Fragment
}