package com.usacheow.coredata.network.error

import com.usacheow.coredata.R
import javax.inject.Inject

class ErrorProcessorImpl
@Inject constructor() : ErrorProcessor {

    override fun process(throwable: Throwable) = when (throwable) {
        is InvalidAccessTokenException -> MappedException(ExceptionType.INVALID_TOKEN, R.string.invalid_token_error_message)

        is NoConnectivityException -> MappedException(ExceptionType.IO, R.string.connection_error_message, throwable.message)

        is HostException -> MappedException(ExceptionType.SERVER, R.string.connection_error_message)

        is ServerException -> MappedException(ExceptionType.SERVER, R.string.server_error_message, throwable.message)

        else -> MappedException(ExceptionType.UNKNOWN, R.string.unknown_error_message)
    }
}