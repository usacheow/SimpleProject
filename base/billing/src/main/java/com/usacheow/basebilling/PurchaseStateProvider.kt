package com.usacheow.basebilling

import com.android.billingclient.api.Purchase
import com.usacheow.basebilling.model.BillingEffect
import com.usacheow.basebilling.model.Product
import javax.inject.Inject
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface PurchaseStateProvider {

    val newPurchasesFlow: SharedFlow<List<Purchase>>

    val isPayedVersionFlow: StateFlow<Boolean>

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