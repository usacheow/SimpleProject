package com.usacheow.corebilling.model

data class BillingEffect<T>(
    val status: BillingConnectionStatus,
    val data: T,
)