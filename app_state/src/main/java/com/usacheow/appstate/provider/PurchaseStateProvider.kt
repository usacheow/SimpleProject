package com.usacheow.appstate.provider

import com.usacheow.corebilling.billing.SimpleBilling
import com.usacheow.coredata.database.UserDataStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseStateProvider @Inject constructor(
    storage: UserDataStorage,
    billingWrapper: SimpleBilling,
) {

    val newPurchasesFlow = billingWrapper.newPurchasesFlow

    val inAppProductsFlow = billingWrapper.inAppProductsFlow

    val subscriptionsFlow = billingWrapper.subscriptionsFlow

    val purchasedInAppProductsFlow = billingWrapper.purchasedInAppProductsFlow

    val purchasedSubscriptionsFlow = billingWrapper.purchasedSubscriptionsFlow

    val isPayedVersionFlow = storage.isPayedVersionFlow
}