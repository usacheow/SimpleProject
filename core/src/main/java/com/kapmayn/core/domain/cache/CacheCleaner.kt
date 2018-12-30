package com.kapmayn.core.domain.cache

interface CacheCleaner {

    fun <T> clearCache(type: Class<T>)

    fun <T> clearCache(type: Class<T>, cacheKey: String)

    fun clearAllCache()

    fun clearStubs()
}
