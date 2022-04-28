package com.usacheow.coredata.network

import androidx.annotation.StringRes
import com.usacheow.corecommon.container.TextValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.usacheow.corecommon.R as CoreR

sealed class ApiError(
    @StringRes val defaultMessageRes: Int,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class InvalidAccessTokenException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreR.string.invalid_token_error_message, message, cause)

    class NoInternetException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreR.string.connection_error_message, message, cause)

    class ServerException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreR.string.server_error_message, message, cause)

    class EmptyResponseException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreR.string.unknown_error_message, message, cause)

    class HostException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreR.string.server_error_message, message, cause)

    class UnknownException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreR.string.unknown_error_message, message, cause)

    class CoroutineException : ApiError(CoreR.string.unknown_error_message)

    class SecurityException : ApiError(CoreR.string.unknown_error_message)

    fun getMessage(): TextValue {
        return message?.let { TextValue.Simple(it) } ?: TextValue.Res(defaultMessageRes)
    }
}

fun Throwable.getMessage(): TextValue = when (this) {
    is ApiError -> getMessage()
    else -> TextValue.Res(CoreR.string.unknown_error_message)
}

@Serializable
data class ErrorDto(
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?
)