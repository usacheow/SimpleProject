package com.usacheow.coredata.error

interface ErrorProcessor {

    fun process(throwable: Throwable): MappedException
}