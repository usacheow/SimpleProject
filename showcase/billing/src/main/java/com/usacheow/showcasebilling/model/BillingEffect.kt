package com.usacheow.showcasebilling.model

data class BillingEffect<T>(
    val status: BillingConnectionStatus,
    val data: T?,
)