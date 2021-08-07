package com.usacheow.corebilling

import com.usacheow.coredata.database.UserDataStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PurchaseStateProvider @Inject constructor(
    storage: UserDataStorage,
    private val billingWrapper: SimpleBilling,
) {

    /*
    * stream of new purchases
    * */
    val newPurchasesFlow = billingWrapper.newPurchasesFlow

    /*
    * paid app status
    * */
    val isPayedVersionFlow = storage.isPayedVersionFlow

    suspend fun getInAppProducts() = billingWrapper.getInAppProducts()

    suspend fun getSubscribeProducts() = billingWrapper.getSubscribeProducts()

    suspend fun getInAppPurchases() = billingWrapper.getInAppPurchases()

    suspend fun getSubscribePurchases() = billingWrapper.getSubscribePurchases()
}