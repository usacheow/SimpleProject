package com.usacheow.coreui.billing

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
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface SimpleBilling : BillingWrapper, BillingMediator

class SimpleBillingImpl(
    private val context: Context,
) : SimpleBilling {

    private var onPurchasesUpdated: ((List<Purchase>) -> Unit)? = null

    private val purchasesUpdatedListener = PurchasesUpdatedListener(::handlePurchase)
    private val acknowledgePurchaseResponseListener = AcknowledgePurchaseResponseListener {
        if (it.responseCode == BillingClient.BillingResponseCode.OK) {
            // todo: Handle the success of the acknowledge operation.
        }
    }
    private val consumeResponseListener = ConsumeResponseListener { billingResult, outToken ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            // todo: Handle the success of the consume operation.
        }
    }

    private var billingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    override suspend fun setup(onPurchasesUpdated: (List<Purchase>) -> Unit): BillingConnectionStatus {
        this.onPurchasesUpdated = onPurchasesUpdated
        return getConnectionStatus()
    }

    override suspend fun fetchProducts(type: ProductType): BillingEffect<List<Product>> {
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

    override suspend fun fetchPurchases(type: ProductType): BillingEffect<List<Purchase>> {
        val status = tryConnectToBillingClient()
        if (status != BillingConnectionStatus.SUCCESS) {
            return BillingEffect(status, emptyList())
        }

        val result = billingClient.queryPurchases(type.code).purchasesList
        return BillingEffect(status, result ?: emptyList())
    }

    override fun openBillingScreen(product: Product, activity: Activity) {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(product.details)
            .build()
        billingClient.launchBillingFlow(activity, flowParams)
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

    private suspend fun tryConnectToBillingClient(): BillingConnectionStatus {
        return (0 until 3).map {
            getConnectionStatus().also {
                if (it == BillingConnectionStatus.IN_PROCESS) {
                    delay(500)
                }
            }
        }.last()
    }

    private fun handlePurchase(billingResult: BillingResult, purchases: List<Purchase>?) {
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

        onPurchasesUpdated?.invoke(purchases)
    }

    private fun handleConsumablePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.consumeAsync(consumeParams, consumeResponseListener)
    }

    private fun handleNonConsumablePurchase(purchase: Purchase) {
        if (purchase.purchaseState != Purchase.PurchaseState.PURCHASED || purchase.isAcknowledged) {
            return
        }

        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener)
    }
}