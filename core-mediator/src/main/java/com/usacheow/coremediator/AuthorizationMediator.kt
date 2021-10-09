package com.usacheow.coremediator

import androidx.navigation.NavDirections

interface AuthorizationMediator {

    fun getPinCodeFlowDirection(nextScreenDirection: NavDirections): NavDirections

    fun getSignInFlowDirection(nextScreenDirection: NavDirections): NavDirections

    fun getSignInWithPhoneFlowDirection(nextScreenDirection: NavDirections): NavDirections

    fun getSignUpFlowDirection(nextScreenDirection: NavDirections): NavDirections
}