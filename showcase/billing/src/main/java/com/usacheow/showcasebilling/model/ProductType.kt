package com.usacheow.showcasebilling.model

import com.android.billingclient.api.BillingClient

enum class ProductType(val code: String) {
    IN_APP(BillingClient.SkuType.INAPP),
    SUBSCRIBE(BillingClient.SkuType.SUBS),
}