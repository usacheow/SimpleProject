package com.usacheow.core.money

import com.google.gson.annotations.SerializedName
import java.util.Currency

enum class CurrencyType {
    @SerializedName("RUB") RUB,
    @SerializedName("USD") USD,
    @SerializedName("EUR") EUR,
    @SerializedName("GBP") GBP,
    @SerializedName("HKD") HKD,
    @SerializedName("CHF") CHF,
    @SerializedName("JPY") JPY,
    @SerializedName("CNY") CNY,
    @SerializedName("TRY") TRY;

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