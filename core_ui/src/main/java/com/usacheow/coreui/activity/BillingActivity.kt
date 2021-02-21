package com.usacheow.coreui.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.usacheow.coreui.delegate.BillingDelegate
import org.solovyev.android.checkout.Purchase

abstract class BillingActivity<VIEW_BINDING : ViewBinding> : SimpleActivity<VIEW_BINDING>() {

    private val billingDelegate by lazy { BillingDelegate() }

    protected val billing get() = billingDelegate.billing

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        billingDelegate.createBilling(
            activity = this,
            onFullVersionFound = ::onFullVersionFound,
            onSuccessPurchase = ::onSuccessPurchase,
            onErrorPurchase = ::onErrorPurchase,
        )
    }

    override fun onDestroy() {
        billingDelegate.destroyBilling()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        billingDelegate.onBillingResult(requestCode, resultCode, data)
    }

    protected open fun onFullVersionFound() {

    }

    protected open fun onSuccessPurchase(result: Purchase) {

    }

    protected open fun onErrorPurchase(response: Int, e: Exception) {

    }
}