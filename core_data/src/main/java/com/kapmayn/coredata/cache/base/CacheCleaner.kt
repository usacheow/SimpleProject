package com.kapmayn.coredata.cache.base

interface CacheCleaner {

    fun <T> clearCache(type: Class<T>)

    fun <T> clearCache(type: Class<T>, cacheKey: String)

    fun clearAllCache()
}
