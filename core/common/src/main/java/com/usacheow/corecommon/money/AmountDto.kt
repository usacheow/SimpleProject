package com.usacheow.corecommon.money

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmountDto(
    @SerialName("currency") val currency: CurrencyType,
    @SerialName("value") val value: Double,
)