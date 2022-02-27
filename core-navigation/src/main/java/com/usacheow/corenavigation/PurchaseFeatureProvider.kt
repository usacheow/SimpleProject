package com.usacheow.corenavigation

import com.usacheow.core.navigation.FeatureNavDirection

interface PurchaseFeatureProvider {

    fun getPurchaseFlowDirection(): FeatureNavDirection
}