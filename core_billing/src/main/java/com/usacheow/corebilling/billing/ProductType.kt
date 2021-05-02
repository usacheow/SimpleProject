package com.usacheow.corebilling.billing

import com.android.billingclient.api.BillingClient

enum class ProductType(val code: String) {
    IN_APP(BillingClient.SkuType.INAPP),
    SUBSCRIPTIONS(BillingClient.SkuType.SUBS),
}