package com.usacheow.coremediator

import androidx.fragment.app.Fragment

interface AuthorizationMediator {

    fun getAuthContainerFragment(): Fragment

    fun getPinCodeFragment(): Fragment
}