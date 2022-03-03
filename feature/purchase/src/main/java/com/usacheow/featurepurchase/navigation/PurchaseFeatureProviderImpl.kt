package com.usacheow.featurepurchase.navigation

import com.usacheow.corenavigation.PurchaseFeatureProvider
import com.usacheow.corenavigation.base.screen
import javax.inject.Inject
import com.example.featurepurchase.R as FeatureR

class PurchaseFeatureProviderImpl @Inject constructor() : PurchaseFeatureProvider {

    override fun getPurchaseFlowDirection() = screen(FeatureR.id.purchase_nav_graph)
}