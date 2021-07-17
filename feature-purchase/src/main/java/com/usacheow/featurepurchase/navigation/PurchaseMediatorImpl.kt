package com.usacheow.featurepurchase.navigation

import com.example.featurepurchase.R
import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coremediator.PurchaseMediator
import javax.inject.Inject

class PurchaseMediatorImpl @Inject constructor() : PurchaseMediator {

    override fun getPurchaseFlowDirection() = FeatureNavDirection(R.id.purchase_nav_graph)
}