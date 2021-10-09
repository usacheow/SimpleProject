package com.usacheow.coredata.network

import android.content.res.Resources
import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.usacheow.core.TextSource
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coredata.R

sealed class ApiError(
    @StringRes val defaultMessageRes: Int,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class InvalidAccessTokenException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.invalid_token_error_message, message, cause)

    class NoInternetException(
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

    class HostException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.server_error_message, message, cause)

    class UnknownException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(R.string.unknown_error_message, message, cause)

    class CoroutineException : ApiError(R.string.unknown_error_message)

    fun getMessage(resources: ResourcesWrapper): String {
        return message ?: resources.getString(defaultMessageRes)
    }

    fun getMessage(): TextSource {
        return message?.let { TextSource.Simple(it) } ?: TextSource.Res(defaultMessageRes)
    }
}

fun Throwable.getMessage(resources: ResourcesWrapper): String = when (this) {
    is ApiError -> getMessage(resources)
    else -> resources.getString(R.string.unknown_error_message)
}

fun Throwable.getMessage(): TextSource = when (this) {
    is ApiError -> getMessage()
    else -> TextSource.Res(R.string.unknown_error_message)
}

data class ErrorDto(
    @SerializedName("type") val type: String?,
    @SerializedName("message") val message: String?
)