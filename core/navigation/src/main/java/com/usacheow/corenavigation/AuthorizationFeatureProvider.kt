package com.usacheow.corenavigation

import com.usacheow.corecommon.navigation.FeatureNavDirection

interface AuthorizationFeatureProvider {

    fun getPinCodeFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection

    fun getSignInFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection

    fun getSignInWithPhoneFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection

    fun getSignUpFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection
}