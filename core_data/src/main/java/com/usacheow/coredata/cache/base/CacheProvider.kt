package com.usacheow.coredata.cache.base

interface CacheProvider : CacheCleaner {

    fun <T> get(type: Class<T>, key: String, memoryCacheTime: Long): T?

    fun <T : Any> save(data: T, key: String)
}
