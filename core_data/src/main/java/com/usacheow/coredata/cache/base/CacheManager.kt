package com.usacheow.coredata.cache.base

interface CacheManager : CacheCleaner {

    fun <T> loadData(resultClass: Class<T>, key: String, memoryCacheTime: Long): T?

    fun <T : Any> saveData(data: T, key: String)
}
