package com.usacheow.coredata.cache

interface CacheProvider : CacheCleaner {

    fun <T> get(type: Class<T>, key: String, memoryCacheTimeInMilliseconds: Long): T?

    fun <T : Any> save(data: T, key: String)
}