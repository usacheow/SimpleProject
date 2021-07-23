package com.usacheow.corebilling

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.AcknowledgePurchaseResponseListener
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetailsParams
import com.android.billingclient.api.acknowledgePurchase
import com.android.billingclient.api.consumePurchase
import com.android.billingclient.api.queryPurchasesAsync
import com.android.billingclient.api.querySkuDetails
import com.usacheow.corebilling.model.BillingConnectionStatus
import com.usacheow.corebilling.model.BillingEffect
import com.usacheow.corebilling.model.Product
import com.usacheow.corebilling.model.ProductType
import com.usacheow.corebilling.model.Sku
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface SimpleBilling : BillingRouter {

    val newPurchasesFlow: SharedFlow<List<Purchase>>

    val inAppProductsFlow: Flow<BillingEffect<List<Product>>>

    val subscriptionsFlow: Flow<BillingEffect<List<Product>>>

    val purchasedInAppProductsFlow: Flow<BillingEffect<List<Purchase>>>

    val purchasedSubscriptionsFlow: Flow<BillingEffect<List<Purchase>>>
}

interface BillingRouter {

    fun openBillingScreen(product: Product, activity: Activity)
}

class SimpleBillingImpl @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationCoroutineScope scope: CoroutineScope,
) : SimpleBilling {

    override val newPurchasesFlow = MutableSharedFlow<List<Purchase>>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override val inAppProductsFlow = flow {
        emit(fetchProducts(ProductType.IN_APP))
    }

    override val subscriptionsFlow = flow {
        emit(fetchProducts(ProductType.SUBSCRIPTIONS))
    }

    override val purchasedInAppProductsFlow = flow {
        emit(fetchPurchases(ProductType.IN_APP))
    }

    override val purchasedSubscriptionsFlow = flow {
        emit(fetchPurchases(ProductType.SUBSCRIPTIONS))
    }

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            scope.launch {
                purchases.forEach { handlePurchase(it) }
            }
            newPurchasesFlow.tryEmit(purchases)
        }
    }

    private var billingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    init {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) = Unit
            override fun onBillingServiceDisconnected() = Unit
        })
    }

    override fun openBillingScreen(product: Product, activity: Activity) {
        val flowParams = BillingFlowParams.newBuilder().setSkuDetails(product.details).build()
        billingClient.launchBillingFlow(activity, flowParams)
    }

    private suspend fun fetchProducts(type: ProductType): BillingEffect<List<Product>> {
        val status = tryConnectToBillingClient()
        if (status != BillingConnectionStatus.SUCCESS) {
            return BillingEffect(status, emptyList())
        }

        val skuList = Sku.byType(type).map { it.code }
        val params = SkuDetailsParams.newBuilder()
            .setSkusList(skuList)
            .setType(type.code)
            .build()

        val result = withContext(Dispatchers.IO) {
            billingClient.querySkuDetails(params)
        }
        val products = when (result.billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> result.skuDetailsList?.map { Product(it) }.orEmpty()
            else -> emptyList()
        }

        return BillingEffect(status = status, data = products)
    }

    private suspend fun fetchPurchases(type: ProductType): BillingEffect<List<Purchase>> {
        val status = tryConnectToBillingClient()
        if (status != BillingConnectionStatus.SUCCESS) {
            return BillingEffect(status, emptyList())
        }

        val purchases = billingClient.queryPurchasesAsync(type.code).purchasesList
        purchases.forEach { handlePurchase(it) }
        return BillingEffect(status = status, data = purchases)
    }

    private suspend fun handlePurchase(purchases: Purchase) {
        suspend fun handleConsumablePurchase(purchase: Purchase) = withContext(Dispatchers.IO) {
            val params = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            billingClient.consumePurchase(params)
        }

        suspend fun handleNonConsumablePurchase(purchase: Purchase) = withContext(Dispatchers.IO) {
            if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED || purchase.isAcknowledged) {
                return@withContext null
            }

            val params = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            billingClient.acknowledgePurchase(params)
        }

        val sku = Sku.byCode(purchases.skus.firstOrNull().orEmpty()) ?: return

        when (sku.isConsumable) {
            true -> handleConsumablePurchase(purchases)
            false -> handleNonConsumablePurchase(purchases)
        }
    }

    private suspend fun tryConnectToBillingClient(): BillingConnectionStatus {
        return (0 until 3).map { getConnectionStatus() }.last()
    }

    private suspend fun getConnectionStatus(): BillingConnectionStatus {
        val code = if (billingClient.isReady) {
            BillingClient.BillingResponseCode.OK
        } else {
            suspendCoroutine {
                billingClient.startConnection(object : BillingClientStateListener {
                    override fun onBillingSetupFinished(billingResult: BillingResult) =
                        it.resume(billingResult.responseCode)

                    override fun onBillingServiceDisconnected() =
                        it.resume(BillingClient.BillingResponseCode.SERVICE_DISCONNECTED)
                })
            }
        }

        return BillingConnectionStatus.get(code)
    }
}