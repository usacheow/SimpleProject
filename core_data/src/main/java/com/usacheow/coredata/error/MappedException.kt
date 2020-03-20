package com.usacheow.coredata.error

data class MappedException(
    private val detailMessage: String,
    private val type: ExceptionType
)

enum class ExceptionType {

    SERVER,
    IO,
    INVALID_TOKEN,
    UNKNOWN
}