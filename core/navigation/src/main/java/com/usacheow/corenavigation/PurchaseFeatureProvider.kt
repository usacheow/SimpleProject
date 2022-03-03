package com.usacheow.corenavigation

import com.usacheow.corecommon.navigation.FeatureNavDirection

interface PurchaseFeatureProvider {

    fun getPurchaseFlowDirection(): FeatureNavDirection
}