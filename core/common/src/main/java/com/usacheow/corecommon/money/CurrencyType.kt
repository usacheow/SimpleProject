package com.usacheow.corecommon.money

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Currency

@Serializable
enum class CurrencyType {
    @SerialName("RUB") RUB,
    @SerialName("USD") USD,
    @SerialName("EUR") EUR,
    @SerialName("GBP") GBP,
    @SerialName("HKD") HKD,
    @SerialName("CHF") CHF,
    @SerialName("JPY") JPY,
    @SerialName("CNY") CNY,
    @SerialName("TRY") TRY;

    companion object {
        val defaultCurrency get() = USD
    }

    val symbol get() = runCatching {
        Currency.getInstance(name).symbol
    }.getOrNull().orEmpty()

    val displayName get() = runCatching {
        Currency.getInstance(name).displayName
    }.getOrNull().orEmpty()
}