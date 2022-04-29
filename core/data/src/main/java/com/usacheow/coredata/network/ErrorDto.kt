package com.usacheow.coredata.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?
)