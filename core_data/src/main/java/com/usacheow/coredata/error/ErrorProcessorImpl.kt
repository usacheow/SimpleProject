package com.usacheow.coredata.error

import android.content.res.Resources
import androidx.annotation.StringRes
import com.usacheow.coredata.R
import java.io.IOException
import javax.inject.Inject

class ErrorProcessorImpl
@Inject constructor(
    private val resources: Resources
) : ErrorProcessor {

    override fun process(throwable: Throwable) = when (throwable) {
        is InvalidAccessTokenException -> MappedException(getString(R.string.invalid_token_error_message), ExceptionType.INVALID_TOKEN)
        is ServerException -> MappedException(getErrorMessage(throwable.message, R.string.server_error_message), ExceptionType.SERVER)
        is IOException -> MappedException(getErrorMessage(throwable.message, R.string.connection_error_message), ExceptionType.IO)
        else -> MappedException(getString(R.string.unknown_error_message), ExceptionType.UNKNOWN)
    }

    private fun getErrorMessage(message: String?, @StringRes defaultMessageResId: Int) = when (message.isNullOrBlank()) {
        true -> getString(defaultMessageResId)
        false -> message
    }

    private fun getString(@StringRes string: Int): String = resources.getString(string)
}