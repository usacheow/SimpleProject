package com.kapmayn.core

import android.app.Activity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams

private const val APP_SKU_ID = ""

class BillingDelegate : Billing {

    override var onPayCompleteAction: () -> Unit = { }

    private var mBillingClient: BillingClient? = null
    private val mSkuDetailsMap = hashMapOf<String, SkuDetails>()

    override fun init(activity: Activity) {
        mBillingClient = BillingClient.newBuilder(activity)
            .setListener { responseCode, purchases ->
                if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
                    onPayCompleteAction()
                }
            }.build()
            .also {
                it.startConnection(CustomBillingClientStateListener())
            }
    }

    override fun launchBilling(activity: Activity, skuId: String) {
        val billingFlowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(mSkuDetailsMap[skuId])
            .build()
        mBillingClient?.launchBillingFlow(activity, billingFlowParams)
    }

    private fun querySkuDetails() {
        val skusList = listOf(APP_SKU_ID)
        val skuDetailsParams = SkuDetailsParams.newBuilder()
            .setSkusList(skusList)
            .setType(BillingClient.SkuType.INAPP)
            .build()

        mBillingClient?.querySkuDetailsAsync(skuDetailsParams) { responseCode, skuDetailsList ->
            if (responseCode == 0) {
                skuDetailsList.forEach { mSkuDetailsMap[it.sku] = it }
            }
        }
    }

    private fun queryPurchases() = mBillingClient?.queryPurchases(BillingClient.SkuType.INAPP)
        ?.purchasesList
        ?: emptyList()

    private inner class CustomBillingClientStateListener : BillingClientStateListener {

        override fun onBillingSetupFinished(
            @BillingClient.BillingResponse billingResponseCode: Int
        ) {

            if (billingResponseCode == BillingClient.BillingResponse.OK) {
                querySkuDetails()

                queryPurchases().map { it.sku }
                    .firstOrNull { it == APP_SKU_ID }
                    ?.let { onPayCompleteAction() }
            }
        }

        override fun onBillingServiceDisconnected() {}
    }
}