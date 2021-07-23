package com.usacheow.corebilling

import com.usacheow.coredata.database.UserDataStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseStateProvider @Inject constructor(
    storage: UserDataStorage,
    billingWrapper: SimpleBilling,
) {

    /*
    * stream of new purchases
    * */
    val newPurchasesFlow = billingWrapper.newPurchasesFlow

    /*
    * all in-app products
    * */
    val inAppProductsFlow = billingWrapper.inAppProductsFlow

    /*
    * all subscription products
    * */
    val subscriptionsFlow = billingWrapper.subscriptionsFlow

    /*
    * all in-app products purchases
    * */
    val purchasedInAppProductsFlow = billingWrapper.purchasedInAppProductsFlow

    /*
    * all subscriptions
    * */
    val purchasedSubscriptionsFlow = billingWrapper.purchasedSubscriptionsFlow

    /*
    * paid app status
    * */
    val isPayedVersionFlow = storage.isPayedVersionFlow
}