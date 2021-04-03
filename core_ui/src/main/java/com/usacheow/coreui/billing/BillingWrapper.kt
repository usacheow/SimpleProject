package com.usacheow.coreui.billing

import com.android.billingclient.api.Purchase

interface BillingWrapper {

    suspend fun setup(onPurchasesUpdated: (List<Purchase>) -> Unit): BillingConnectionStatus

    suspend fun fetchProducts(type: ProductType): BillingEffect<List<Product>>

    suspend fun fetchPurchases(type: ProductType): BillingEffect<List<Purchase>>
}