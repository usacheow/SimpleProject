package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface PurchaseMediator {

    fun getPurchaseFlowDirection(): FeatureNavDirection
}