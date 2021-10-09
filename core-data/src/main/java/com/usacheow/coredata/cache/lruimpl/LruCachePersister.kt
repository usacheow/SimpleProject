package com.usacheow.coredata.cache.lruimpl

import androidx.collection.LruCache

class LruCachePersister<DATA_TYPE>(
    maxSize: Int
) : LruCache<String, CacheItem<DATA_TYPE>>(maxSize) {

    fun get(cacheKey: String, maxTimeInCache: Long): DATA_TYPE? {
        val cacheItem = get(cacheKey) ?: return null

        val dataIsNotExpired = System.currentTimeMillis() - cacheItem.creationDate <= maxTimeInCache
        return if (dataIsNotExpired) {
            cacheItem.data
        } else {
            remove(cacheKey)
            null
        }
    }

    fun save(data: DATA_TYPE, cacheKey: String) {
        put(cacheKey, CacheItem(data))
    }

    fun clear(cacheKey: String) = remove(cacheKey) != null

    fun clearAll() {
        evictAll()
    }
}

class CacheItem<T>(val data: T) {

    val creationDate = System.currentTimeMillis()
}