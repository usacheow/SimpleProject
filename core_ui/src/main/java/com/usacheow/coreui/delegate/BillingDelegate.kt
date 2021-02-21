package com.usacheow.coreui.delegate

import android.app.Activity
import android.content.Intent
import com.usacheow.coreui.billing.BillingWrapper
import org.solovyev.android.checkout.Purchase

class BillingDelegate {

    var billing: BillingWrapper? = null

    fun createBilling(
        activity: Activity,
        onFullVersionFound: () -> Unit,
        onSuccessPurchase: (Purchase) -> Unit,
        onErrorPurchase: (Int, Exception) -> Unit,
    ) {
//        billing = BillingWrapper(activity).apply {
//            start()
//            checkAvailablePurchase { onFullVersionFound() }
//            subscribeOnPurchases({ onSuccessPurchase(it) }) { response, e -> onErrorPurchase(response, e) }
//        }
    }

    fun destroyBilling() {
//        billing?.stop()
//        billing = null
    }

    fun onBillingResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        billing?.onBillingResult(requestCode, resultCode, data)
    }
}