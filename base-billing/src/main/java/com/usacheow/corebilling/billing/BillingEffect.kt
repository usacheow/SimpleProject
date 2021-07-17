package com.usacheow.corebilling.billing

data class BillingEffect<T>(
    val status: BillingConnectionStatus,
    val data: T,
)