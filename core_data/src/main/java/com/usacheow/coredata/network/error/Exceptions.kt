package com.usacheow.coredata.network.error

class InvalidAccessTokenException(
    detailMessage: String? = null,
    throwable: Throwable? = null
) : Exception(detailMessage, throwable)

class NoConnectivityException(
    detailMessage: String? = null,
    throwable: Throwable? = null
) : Exception(detailMessage, throwable)

class HostException(
    detailMessage: String? = null,
    throwable: Throwable? = null
) : Exception(detailMessage, throwable)

class ServerException(
    detailMessage: String? = null,
    throwable: Throwable? = null
) : Exception(detailMessage, throwable)

class UnknownException(
    detailMessage: String? = null,
    throwable: Throwable? = null
) : Exception(detailMessage, throwable)