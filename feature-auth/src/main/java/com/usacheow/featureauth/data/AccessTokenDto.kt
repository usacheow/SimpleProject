package com.usacheow.featureauth.data

import com.google.gson.annotations.SerializedName

data class AccessTokenDto(
    @SerializedName("access_token")
    val token: String,
)