package com.usacheow.coreui.billing

data class BillingEffect<T>(
    val status: BillingConnectionStatus,
    val data: T,
)