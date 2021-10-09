package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface OnBoardingMediator {

    fun getOnBoardingFlowDirection(nextScreenDirection: FeatureNavDirection): FeatureNavDirection
}