package com.usacheow.corebilling.billing

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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
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
) : SimpleBilling {

    override val newPurchasesFlow = MutableSharedFlow<List<Purchase>>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override val inAppProductsFlow = flow<BillingEffect<List<Product>>> {
        fetchProducts(ProductType.IN_APP)
    }

    override val subscriptionsFlow = flow<BillingEffect<List<Product>>> {
        fetchProducts(ProductType.SUBSCRIPTIONS)
    }

    override val purchasedInAppProductsFlow = flow<BillingEffect<List<Purchase>>> {
        fetchPurchases(ProductType.IN_APP)
    }

    override val purchasedSubscriptionsFlow = flow<BillingEffect<List<Purchase>>> {
        fetchPurchases(ProductType.SUBSCRIPTIONS)
    }

    private val purchasesUpdatedListener = PurchasesUpdatedListener(::handlePurchase)
    private val acknowledgePurchaseResponseListener = AcknowledgePurchaseResponseListener {
        if (it.responseCode == BillingClient.BillingResponseCode.OK) {
            // todo: Handle the success of the acknowledge operation.
        }
    }
    private val consumeResponseListener = ConsumeResponseListener { billingResult, _ ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            // todo: Handle the success of the consume operation.
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

    private suspend fun fetchProducts(type: ProductType): BillingEffect<List<Product>> {
        val status = tryConnectToBillingClient()
        if (status != BillingConnectionStatus.SUCCESS) {
            return BillingEffect(status, emptyList())
        }

        return suspendCoroutine {
            val skuList = Sku.byType(type).map { it.code }
            val params = SkuDetailsParams
                .newBuilder()
                .setSkusList(skuList)
                .setType(type.code)
                .build()

            billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
                val products = when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> skuDetailsList?.map { Product(it) }
                    else -> null
                }
                it.resume(BillingEffect(status, products ?: emptyList()))
            }
        }
    }

    private suspend fun fetchPurchases(type: ProductType): BillingEffect<List<Purchase>> {
        val status = tryConnectToBillingClient()
        if (status != BillingConnectionStatus.SUCCESS) {
            return BillingEffect(status, emptyList())
        }

        val result = billingClient.queryPurchases(type.code).purchasesList
        return BillingEffect(status, result ?: emptyList())
    }

    private suspend fun tryConnectToBillingClient(): BillingConnectionStatus {
        return (0 until 3).map {
            getConnectionStatus().also {
                if (it == BillingConnectionStatus.IN_PROCESS) {
                    delay(500)
                }
            }
        }.last()
    }

    private suspend fun getConnectionStatus(): BillingConnectionStatus {
        val code = if (billingClient.isReady) {
            BillingClient.BillingResponseCode.OK
        } else {
            suspendCoroutine {
                billingClient.startConnection(object : BillingClientStateListener {
                    override fun onBillingSetupFinished(billingResult: BillingResult) {
                        it.resume(billingResult.responseCode)
                    }

                    override fun onBillingServiceDisconnected() {
                        it.resume(BillingClient.BillingResponseCode.SERVICE_DISCONNECTED)
                    }
                })
            }
        }

        return BillingConnectionStatus.get(code)
    }

    private fun handlePurchase(billingResult: BillingResult, purchases: List<Purchase>?) {
        fun handleConsumablePurchase(purchase: Purchase) {
            val consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()

            billingClient.consumeAsync(consumeParams, consumeResponseListener)
        }

        fun handleNonConsumablePurchase(purchase: Purchase) {
            if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED || purchase.isAcknowledged) {
                return
            }

            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()

            billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener)
        }

        if (billingResult.responseCode != BillingClient.BillingResponseCode.OK || purchases == null) {
            return
        }

        purchases.forEach {
            val sku = Sku.byCode(it.sku) ?: return@forEach

            when (sku.isConsumable) {
                true -> handleConsumablePurchase(it)
                false -> handleNonConsumablePurchase(it)
            }
        }

        newPurchasesFlow.tryEmit(purchases)
    }
}