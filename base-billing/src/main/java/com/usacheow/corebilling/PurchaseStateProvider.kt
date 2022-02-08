package com.usacheow.corebilling

import com.android.billingclient.api.Purchase
import com.usacheow.corebilling.model.BillingEffect
import com.usacheow.corebilling.model.Product
import com.usacheow.coredata.database.UserDataStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PurchaseStateProvider {

    val newPurchasesFlow: Flow<List<Purchase>>

    val isPayedVersionFlow: Flow<Boolean>

    suspend fun getInAppProducts(): BillingEffect<List<Product>>
    suspend fun getSubscribeProducts(): BillingEffect<List<Product>>
    suspend fun getInAppPurchases(): BillingEffect<List<Purchase>>
    suspend fun getSubscribePurchases(): BillingEffect<List<Purchase>>
}

class PurchaseStateProviderImpl @Inject constructor(
    private val billingWrapper: SimpleBilling,
) : PurchaseStateProvider {

    /*
    * stream of new purchases
    * */
    override val newPurchasesFlow = billingWrapper.newPurchasesFlow

    /*
    * paid app status
    * */
    override val isPayedVersionFlow = billingWrapper.isPayedVersionFlow

    override suspend fun getInAppProducts() = billingWrapper.getInAppProducts()

    override suspend fun getSubscribeProducts() = billingWrapper.getSubscribeProducts()

    override suspend fun getInAppPurchases() = billingWrapper.getInAppPurchases()

    override suspend fun getSubscribePurchases() = billingWrapper.getSubscribePurchases()
}