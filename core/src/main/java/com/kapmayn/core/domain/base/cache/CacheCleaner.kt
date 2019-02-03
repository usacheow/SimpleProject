package com.kapmayn.core.domain.base.cache

interface CacheCleaner {

    fun <T> clearCache(type: Class<T>)

    fun <T> clearCache(type: Class<T>, cacheKey: String)

    fun clearAllCache()
}
