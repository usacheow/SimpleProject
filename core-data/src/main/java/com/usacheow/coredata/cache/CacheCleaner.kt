package com.usacheow.coredata.cache

interface CacheCleaner {

    fun <T> clear(type: Class<T>)

    fun <T> clear(type: Class<T>, cacheKey: String)

    fun clearAll()
}