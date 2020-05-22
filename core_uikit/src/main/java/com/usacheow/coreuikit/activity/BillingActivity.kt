package com.usacheow.coreuikit.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import com.usacheow.coreuikit.delegate.BillingDelegate
import org.solovyev.android.checkout.Purchase

abstract class BillingActivity : SimpleActivity() {

    protected val billingDelegate by lazy { BillingDelegate() }

    val billingHelper get() = billingDelegate.billingHelper

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        billingDelegate.apply {
            onCreate(this@BillingActivity)
            onFullVersionFound = ::onFullVersionFound
            onSuccessPurchase = ::onSuccessPurchase
            onErrorPurchase = ::onErrorPurchase
        }
    }

    override fun onDestroy() {
        billingDelegate.onDestroy()
        super.onDestroy()
    }

    protected open fun onFullVersionFound() {

    }

    protected open fun onSuccessPurchase(result: Purchase) {

    }

    protected open fun onErrorPurchase(response: Int, e: Exception) {

    }
}