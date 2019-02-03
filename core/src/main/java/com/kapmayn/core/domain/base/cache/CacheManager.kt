package com.kapmayn.core.domain.base.cache

interface CacheManager : CacheCleaner {

    fun <T> loadData(resultClass: Class<T>, key: String, memoryCacheTime: Long): T?

    fun <T : Any> saveData(data: T, key: String)
}
