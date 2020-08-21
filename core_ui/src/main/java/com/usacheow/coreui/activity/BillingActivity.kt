package com.usacheow.coreui.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.usacheow.coreui.delegate.BillingDelegate
import org.solovyev.android.checkout.Purchase

abstract class BillingActivity(@LayoutRes layoutId: Int) : SimpleActivity(layoutId) {

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        billingDelegate.onActivityResult(requestCode, resultCode, data)
    }

    protected open fun onFullVersionFound() {

    }

    protected open fun onSuccessPurchase(result: Purchase) {

    }

    protected open fun onErrorPurchase(response: Int, e: Exception) {

    }
}