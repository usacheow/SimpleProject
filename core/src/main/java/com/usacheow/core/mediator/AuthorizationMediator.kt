package com.usacheow.core.mediator

import androidx.fragment.app.Fragment

interface AuthorizationMediator {

    fun getAuthContainerFragment(): Fragment

    fun getPinCodeFragment(): Fragment
}