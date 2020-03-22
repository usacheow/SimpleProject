package com.usacheow.coredata.network.error

interface ErrorProcessor {

    fun process(throwable: Throwable): MappedException
}