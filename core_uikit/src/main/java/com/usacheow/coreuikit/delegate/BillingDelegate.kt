package com.usacheow.coreuikit.delegate

import android.app.Activity
import com.usacheow.coreuikit.BillingWrapper
import org.solovyev.android.checkout.Purchase

class BillingDelegate {

    var billingHelper: BillingWrapper? = null

    var onFullVersionFound: () -> Unit = {}
    var onSuccessPurchase: (Purchase) -> Unit = {}
    var onErrorPurchase: (Int, Exception) -> Unit = { _, _ -> }

    fun onCreate(activity: Activity) {
//        billingHelper = BillingWrapper(activity).apply {
//            start()
//            checkAvailablePurchase { onFullVersionFound() }
//            createPurchaseFlow({ onSuccessPurchase(it) }) { response, e -> onErrorPurchase(response, e) }
//        }
    }

    fun onDestroy() {
//        billingHelper?.stop()
//        onFullVersionFound = {}
//        onSuccessPurchase = {}
//        onErrorPurchase = { _, _ -> }
    }
}