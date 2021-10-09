package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface MainMediator {

    fun getAFlowDirection(): FeatureNavDirection

    fun getMockFlowDirection(): FeatureNavDirection
}