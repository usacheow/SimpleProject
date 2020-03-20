package com.usacheow.core.mediator

import androidx.fragment.app.Fragment

interface AuthorizationMediator {

    fun getAuthContainer(): Fragment

    fun getPinCodeFragment(): Fragment
}