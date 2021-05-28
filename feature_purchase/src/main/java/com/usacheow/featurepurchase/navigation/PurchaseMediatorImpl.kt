package com.usacheow.featurepurchase.navigation

import com.usacheow.coremediator.PurchaseMediator
import com.usacheow.featurepurchase.fragment.PurchaseModalFragment
import javax.inject.Inject

class PurchaseMediatorImpl @Inject constructor() : PurchaseMediator {

    override fun getPurchaseScreen() = PurchaseModalFragment.newInstance()
}