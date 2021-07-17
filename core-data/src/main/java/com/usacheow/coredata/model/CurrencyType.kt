package com.usacheow.coredata.model

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

    val symbol get() = try {
        Currency.getInstance(name).symbol
    } catch (e: Exception) {
        ""
    }

    val displayName get() = try {
        Currency.getInstance(name).displayName
    } catch (e: Exception) {
        ""
    }
}