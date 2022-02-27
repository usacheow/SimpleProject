package com.usacheow.corenavigation

import com.usacheow.core.navigation.FeatureNavDirection

interface MainFeatureProvider {

    fun getAFlowDirection(): FeatureNavDirection

    fun getMockFlowDirection(): FeatureNavDirection
}