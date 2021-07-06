package com.usacheow.coremediator

import androidx.navigation.NavDirections

interface AuthorizationMediator {

    fun getPinCodeFlowDirection(): NavDirections

    fun getSignInFlowDirection(): NavDirections

    fun getSignInWithPhoneFlowDirection(): NavDirections

    fun getSignUpFlowDirection(): NavDirections
}