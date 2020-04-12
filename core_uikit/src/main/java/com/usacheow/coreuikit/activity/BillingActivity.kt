package com.usacheow.coreuikit.activity

abstract class BillingActivity : ContainerActivity() {

    //todo take token from play console
//    var billingHelper: BillingWrapper? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        billingHelper = BillingWrapper(this).apply {
//            start()
//            subscribeOnPurchase(::onFinishedPurchases)
//            createPurchaseFlow(::onSuccessPurchase, ::onErrorPurchase)
//        }
//    }
//
//    override fun onDestroy() {
//        billingHelper?.stop()
//        super.onDestroy()
//    }
//
//    protected open fun onFinishedPurchases(sku: String) {
//
//    }
//
//    protected open fun onSuccessPurchase(result: Purchase) {
//
//    }
//
//    protected open fun onErrorPurchase(response: Int, e: Exception) {
//
//    }
}