package com.usacheow.corecommon.money

import com.usacheow.corecommon.formatDigits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val SUM_FORMAT = "%s %s"

@Serializable
data class AmountDto(
    @SerialName("currency") val currency: CurrencyType,
    @SerialName("value") val value: Double,
) {

    fun toAmount() = SUM_FORMAT.format(value.formatDigits(), currency.symbol)
}