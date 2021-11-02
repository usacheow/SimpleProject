package com.usacheow.core.money

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class AmountDto(
    @SerialName("currency") val currency: CurrencyType,
    @SerialName("value") val value: Double,
)