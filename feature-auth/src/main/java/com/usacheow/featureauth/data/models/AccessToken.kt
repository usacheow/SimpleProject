package com.usacheow.featureauth.data.models

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    val token: String,
)