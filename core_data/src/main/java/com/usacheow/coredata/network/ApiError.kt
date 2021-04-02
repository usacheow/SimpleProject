package com.usacheow.coredata.network

import android.content.res.Resources
import com.google.gson.annotations.SerializedName
import com.usacheow.coredata.R

sealed class ApiError(
    private val defaultMessageId: Int,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class InvalidAccessTokenException(
        val responseCode: Int = 0,
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.invalid_token_error_message, message, cause)

    class ApiException(
        val responseCode: Int = 0,
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.server_error_message, message, cause)

    class NoConnectivityException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.connection_error_message, message, cause)

    class ServerException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.server_error_message, message, cause)

    class EmptyResponseException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.unknown_error_message, message, cause)

    class DataMappingException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.unknown_error_message, message, cause)

    class HostException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.server_error_message, message, cause)

    class UnknownException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.unknown_error_message, message, cause)

    class CoroutineException() : ApiError(R.string.unknown_error_message)

    fun getMessage(resources: Resources): String {
        return message ?: resources.getString(defaultMessageId)
    }
}

data class ErrorMessage(
    @SerializedName("type") val type: String?,
    @SerializedName("message") val message: String?
)