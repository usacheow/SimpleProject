package com.usacheow.corenavigation

import com.usacheow.corecommon.navigation.FeatureNavDirection

interface MainFeatureProvider {

    fun getAFlowDirection(): FeatureNavDirection

    fun getMockFlowDirection(): FeatureNavDirection
}