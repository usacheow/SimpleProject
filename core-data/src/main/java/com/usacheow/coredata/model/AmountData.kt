package com.usacheow.coredata.model

import com.google.gson.annotations.SerializedName

data class AmountData(
    @SerializedName("currency") val currency: CurrencyType,
    @SerializedName("value") val value: Double,
)