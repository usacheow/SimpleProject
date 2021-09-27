package com.usacheow.coredata.dto

import com.google.gson.annotations.SerializedName

data class AmountDto(
    @SerializedName("currency") val currency: CurrencyType,
    @SerializedName("value") val value: Double,
)