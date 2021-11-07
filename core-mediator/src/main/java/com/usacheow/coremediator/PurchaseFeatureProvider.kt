package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface PurchaseFeatureProvider {

    fun getPurchaseFlowDirection(): FeatureNavDirection
}