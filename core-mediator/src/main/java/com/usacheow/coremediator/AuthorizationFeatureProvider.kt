package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface AuthorizationFeatureProvider {

    fun getPinCodeFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection

    fun getSignInFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection

    fun getSignInWithPhoneFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection

    fun getSignUpFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection
}