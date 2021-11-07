package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface MainFeatureProvider {

    fun getAFlowDirection(): FeatureNavDirection

    fun getMockFlowDirection(): FeatureNavDirection
}