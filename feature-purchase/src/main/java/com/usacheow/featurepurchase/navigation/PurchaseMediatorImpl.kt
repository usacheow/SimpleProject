package com.usacheow.featurepurchase.navigation

import com.example.featurepurchase.R
import com.usacheow.coremediator.PurchaseMediator
import com.usacheow.coreui.utils.navigation.screen
import javax.inject.Inject

class PurchaseMediatorImpl @Inject constructor() : PurchaseMediator {

    override fun getPurchaseFlowDirection() = screen(R.id.purchase_nav_graph)
}