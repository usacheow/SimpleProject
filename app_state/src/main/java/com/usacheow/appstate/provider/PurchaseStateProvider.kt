package com.usacheow.appstate.provider

import com.usacheow.corebilling.billing.SimpleBilling
import com.usacheow.coredata.database.Storage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseStateProvider @Inject constructor(
    private val storage: Storage,
    billingWrapper: SimpleBilling,
) {

    val newPurchasesFlow = billingWrapper.newPurchasesFlow

    val inAppProductsFlow = billingWrapper.inAppProductsFlow

    val subscriptionsFlow = billingWrapper.subscriptionsFlow

    val purchasedInAppProductsFlow = billingWrapper.purchasedInAppProductsFlow

    val purchasedSubscriptionsFlow = billingWrapper.purchasedSubscriptionsFlow

    fun getPayedVersion() = storage.isPayedVersion
}