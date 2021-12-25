package com.usacheow.featurepurchase.navigation

import com.usacheow.coremediator.PurchaseFeatureProvider
import com.usacheow.coreui.utils.navigation.screen
import javax.inject.Inject
import com.example.featurepurchase.R as FeatureR

class PurchaseFeatureProviderImpl @Inject constructor() : PurchaseFeatureProvider {

    override fun getPurchaseFlowDirection() = screen(FeatureR.id.purchase_nav_graph)
}