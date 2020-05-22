package com.usacheow.coreuikit

import android.app.Activity

const val ITEM_1_SKU_ID = "ITEM_1_SKU_ID"

class BillingWrapper(activity: Activity) {

    //todo take token from play console
//    private val checkout = Checkout.forActivity(activity, (activity.application as DiApp).billing)
//    private val inventory by lazy { checkout.makeInventory() }
//
//    companion object {
//
//        fun initBilling(context: Context) = Billing(context, object : Billing.DefaultConfiguration() {
//
//            override fun getPublicKey() = Encryption.decrypt(
//                "TOKEN",
//                "KEY"
//            )
//        })
//    }
//
//    fun start() {
//        checkout.start()
//    }
//
//    fun stop() {
//        inventory.cancel()
//        checkout.stop()
//    }
//
//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        checkout.onActivityResult(requestCode, resultCode, data)
//    }
//
//    fun checkAvailablePurchase(onAvailableAction: () -> Unit) {
//        val inventoryRequest = Inventory.Request.create()
//            .loadAllPurchases()
//            .loadSkus(ProductTypes.IN_APP, ITEM_1_SKU_ID)
//
//        inventory.load(inventoryRequest) { allPurchases ->
//            allPurchases[ProductTypes.IN_APP].purchases.firstOrNull { it.sku == ITEM_1_SKU_ID }?.let {
//                onAvailableAction()
//            }
//        }
//    }
//
//    fun createPurchaseFlow(onSuccessPurchase: (Purchase) -> Unit, onErrorPurchase: (Int, Exception) -> Unit) {
//        checkout.createPurchaseFlow(
//            object : RequestListener<Purchase> {
//
//                override fun onSuccess(result: Purchase) {
//                    onSuccessPurchase(result)
//                }
//
//                override fun onError(response: Int, e: Exception) {
//                    onErrorPurchase(response, e)
//                }
//            }
//        )
//    }
//
//    fun buyItem1Version() {
//        checkout.whenReady(object : Checkout.EmptyListener() {
//            override fun onReady(requests: BillingRequests) {
//                requests.purchase(ProductTypes.IN_APP, ITEM_1_SKU_ID, null, checkout.purchaseFlow)
//            }
//        })
//    }
//
//    fun useItem(purchase: Purchase) {
//        checkout.whenReady(object : Checkout.EmptyListener() {
//            override fun onReady(requests: BillingRequests) {
//                requests.consume(purchase.token, object : RequestListener<Any> {
//                    override fun onSuccess(result: Any) {
//                    }
//
//                    override fun onError(response: Int, e: Exception) {
//                    }
//                })
//            }
//        })
//    }
//
//    fun getPrices(callback: (Prices) -> Unit) {
//        val inventoryRequest = Inventory.Request.create()
//            .loadAllPurchases()
//            .loadSkus(ProductTypes.IN_APP, ITEM_1_SKU_ID)
//
//        inventory.load(inventoryRequest) { allPurchases ->
//            val purchases = allPurchases[ProductTypes.IN_APP]
//            callback(Prices(
//                item1 = purchases.getSku(ITEM_1_SKU_ID)?.price
//            ))
//        }
//    }
}

data class Prices(
    val item1: String?
)