package com.usacheow.authorization.data.models

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    val token: String
)