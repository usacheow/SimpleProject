package com.usacheow.basebilling.model

import com.android.billingclient.api.BillingClient

enum class BillingConnectionStatus(private vararg val codes: Int) {

    SUCCESS(
        BillingClient.BillingResponseCode.OK
    ),
    IN_PROCESS(
        BillingClient.BillingResponseCode.DEVELOPER_ERROR
    ),
    ERROR(
        BillingClient.BillingResponseCode.SERVICE_TIMEOUT,
        BillingClient.BillingResponseCode.SERVICE_DISCONNECTED,
        BillingClient.BillingResponseCode.ERROR
    ),
    UNAVAILABLE(
        BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED,
        BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE,
        BillingClient.BillingResponseCode.BILLING_UNAVAILABLE
    ),
    UNKNOWN;

    companion object {

        fun get(code: Int) = values().firstOrNull { it.codes.contains(code) } ?: ERROR
    }
}