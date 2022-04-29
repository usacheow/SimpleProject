package com.usacheow.corecommon

import androidx.annotation.StringRes
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.R as CoreR

sealed class AppError(
    @StringRes val defaultMessageRes: Int,
    message: String? = null,
    cause: Exception? = null
) : Exception(message, cause) {

    class InvalidAccessToken(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(CoreR.string.invalid_token_error_message, message, cause)

    class NoInternet(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(CoreR.string.connection_error_message, message, cause)

    class Server(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(CoreR.string.server_error_message, message, cause)

    class Empty(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(CoreR.string.unknown_error_message, message, cause)

    class Host(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(CoreR.string.server_error_message, message, cause)

    class Unknown(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(CoreR.string.unknown_error_message, message, cause)

    class Coroutine : AppError(CoreR.string.unknown_error_message)

    class Security : AppError(CoreR.string.unknown_error_message)

    fun makeUserReadableErrorMessage(): TextValue {
        return message?.let { TextValue.Simple(it) } ?: TextValue.Res(defaultMessageRes)
    }
}

fun Exception.makeUserReadableErrorMessage(): TextValue = when (this) {
    is AppError -> makeUserReadableErrorMessage()
    else -> TextValue.Res(CoreR.string.unknown_error_message)
}