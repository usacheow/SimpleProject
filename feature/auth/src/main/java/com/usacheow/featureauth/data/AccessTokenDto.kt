package com.usacheow.featureauth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenDto(
    @SerialName("access_token")
    val token: String,
)