package com.usacheow.coredata.network

import androidx.annotation.StringRes
import com.usacheow.core.resource.TextSource
import com.usacheow.core.resource.ResourcesWrapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.usacheow.coredata.R as CoreDataR

sealed class ApiError(
    @StringRes val defaultMessageRes: Int,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class InvalidAccessTokenException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreDataR.string.invalid_token_error_message, message, cause)

    class NoInternetException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreDataR.string.connection_error_message, message, cause)

    class ServerException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreDataR.string.server_error_message, message, cause)

    class EmptyResponseException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreDataR.string.unknown_error_message, message, cause)

    class HostException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreDataR.string.server_error_message, message, cause)

    class UnknownException(
        message: String? = null,
        cause: Throwable? = null
    ) : ApiError(CoreDataR.string.unknown_error_message, message, cause)

    class CoroutineException : ApiError(CoreDataR.string.unknown_error_message)

    class SecurityException : ApiError(CoreDataR.string.unknown_error_message)

    fun getMessage(resources: ResourcesWrapper): String {
        return message ?: resources.getString(defaultMessageRes)
    }

    fun getMessage(): TextSource {
        return message?.let { TextSource.Simple(it) } ?: TextSource.Res(defaultMessageRes)
    }
}

fun Throwable.getMessage(resources: ResourcesWrapper): String = when (this) {
    is ApiError -> getMessage(resources)
    else -> resources.getString(CoreDataR.string.unknown_error_message)
}

fun Throwable.getMessage(): TextSource = when (this) {
    is ApiError -> getMessage()
    else -> TextSource.Res(CoreDataR.string.unknown_error_message)
}

@Serializable
data class ErrorDto(
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?
)