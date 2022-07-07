package com.usacheow.corecommon.model

import androidx.annotation.StringRes
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.container.textValue
import com.usacheow.corecommon.R as CoreR

sealed class AppError(
    @StringRes val defaultMessageRes: Int = CoreR.string.unknown_error_message,
    message: String? = null,
    cause: Exception? = null,
    val code: Int? = null,
) : Exception(message, cause) {

    class Unknown(
        message: String? = null,
        cause: Exception? = null
    ) : AppError(message = message, cause = cause)

    class Custom(
        message: String? = null,
        cause: Exception? = null,
        code: Int? = null,
        val displayMessage: String? = null,
    ) : AppError(message = message, cause = cause, code = code)

    fun makeUserReadableErrorMessage(): TextValue {
        return if (this is Custom && displayMessage != null) {
            displayMessage.textValue()
        } else {
            message?.textValue() ?: defaultMessageRes.textValue()
        }
    }
}

fun Exception.makeUserReadableErrorMessage(): TextValue = when (this) {
    is AppError -> makeUserReadableErrorMessage()
    else -> CoreR.string.unknown_error_message.textValue()
}