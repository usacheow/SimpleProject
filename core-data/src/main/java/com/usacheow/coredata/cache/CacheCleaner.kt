package com.usacheow.coredata.cache

interface CacheCleaner {

    suspend fun <T> clear(clazz: Class<T>, key: String)

    suspend fun clearAll()
}