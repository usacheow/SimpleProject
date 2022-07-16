package com.usacheow.basebilling

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetailsParams
import com.android.billingclient.api.acknowledgePurchase
import com.android.billingclient.api.consumePurchase
import com.android.billingclient.api.queryPurchasesAsync
import com.android.billingclient.api.querySkuDetails
import com.usacheow.basebilling.model.BillingConnectionStatus
import com.usacheow.basebilling.model.BillingEffect
import com.usacheow.basebilling.model.Product
import com.usacheow.basebilling.model.ProductType
import com.usacheow.basebilling.model.Sku
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import com.usacheow.coredata.coroutine.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface SimpleBilling : BillingRouter {

    val isPayedVersionFlow: StateFlow<Boolean>

    val newPurchasesFlow: SharedFlow<List<Purchase>>

    suspend fun getInAppProducts(): BillingEffect<List<Product>>

    suspend fun getSubscribeProducts(): BillingEffect<List<Product>>

    suspend fun getInAppPurchases(): BillingEffect<List<Purchase>>

    suspend fun getSubscribePurchases(): BillingEffect<List<Purchase>>
}

interface BillingRouter {

    fun openBillingScreen(product: Product, activity: Activity)
}

class SimpleBillingImpl @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationCoroutineScope private val scope: CoroutineScope,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SimpleBilling {

    override val isPayedVersionFlow = MutableStateFlow(false)

    override val newPurchasesFlow = MutableSharedFlow<List<Purchase>>(replay = 1)

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            scope.launch {
                handlePurchases(purchases)
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
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(product.details)
            .build()
        billingClient.launchBillingFlow(activity, flowParams)
    }

    override suspend fun getInAppProducts() = fetchProducts(ProductType.IN_APP)

    override suspend fun getSubscribeProducts() = fetchProducts(ProductType.SUBSCRIBE)

    override suspend fun getInAppPurchases() = fetchPurchases(ProductType.IN_APP)

    override suspend fun getSubscribePurchases() = fetchPurchases(ProductType.SUBSCRIBE)

    private suspend fun fetchProducts(type: ProductType): BillingEffect<List<Product>> = billingCall {
        val params = SkuDetailsParams.newBuilder()
            .setSkusList(Sku.byType(type))
            .setType(type.code)
            .build()
        val result = billingClient.querySkuDetails(params)

        return@billingCall when (result.billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> result.skuDetailsList
                ?.map { Product(it) }
                .orEmpty()

            else -> null
        }
    }

    private suspend fun fetchPurchases(type: ProductType): BillingEffect<List<Purchase>> = billingCall {
        val purchases = billingClient.queryPurchasesAsync(type.code).purchasesList
        handlePurchases(purchases)
        return@billingCall purchases
    }

    private suspend fun handlePurchases(purchases: List<Purchase>) = purchases.forEach {
        handlePurchase(it)
    }

    private suspend fun handlePurchase(purchase: Purchase) {
        suspend fun handleConsumablePurchase(purchase: Purchase) = withContext(ioDispatcher) {
            val params = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            billingClient.consumePurchase(params)
        }

        suspend fun handleNonConsumablePurchase(purchase: Purchase) = withContext(ioDispatcher) {
            if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED || purchase.isAcknowledged) {
                return@withContext null
            }

            val params = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            billingClient.acknowledgePurchase(params)
        }

        val isConsumableSku = purchase.skus
            .firstOrNull()
            .orEmpty()
            .run(Sku::byCode)
            ?.isConsumable
        when (isConsumableSku) {
            true -> handleConsumablePurchase(purchase)
            false -> handleNonConsumablePurchase(purchase)
            null -> {}
        }
    }

    private suspend inline fun <T : Any> billingCall(
        crossinline block: suspend () -> T?,
    ): BillingEffect<T> = withContext(ioDispatcher) {
        var status = BillingConnectionStatus.UNKNOWN
        (0..2).firstOrNull {
            status = getConnectionStatus()
            if (status != BillingConnectionStatus.SUCCESS) {
                delay(300)
                return@firstOrNull false
            }
            return@firstOrNull true
        } ?: return@withContext BillingEffect(status, null)

        return@withContext BillingEffect(status, block())
    }

    private suspend fun getConnectionStatus(): BillingConnectionStatus {
        val code = when {
            billingClient.isReady -> BillingClient.BillingResponseCode.OK

            else -> suspendCoroutine {
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