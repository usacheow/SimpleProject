package com.usacheow.coredata.error

import java.io.IOException

class InvalidAccessTokenException(detailMessage: String?) : IOException(detailMessage)

class ServerException(detailMessage: String? = null, throwable: Throwable? = null) : Exception(detailMessage, throwable)