package com.usacheow.coredata.network.error

import androidx.annotation.StringRes

data class MappedException(
    private val type: ExceptionType,
    @StringRes private val defaultMessageId: Int,
    private val detailMessage: String? = null
)

enum class ExceptionType {

    SERVER,
    IO,
    INVALID_TOKEN,
    UNKNOWN
}