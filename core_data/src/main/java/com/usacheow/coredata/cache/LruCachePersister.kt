package com.usacheow.coredata.cache

import androidx.collection.LruCache

class LruCachePersister<T>(
    private val lruCache: LruCache<String, CacheItem<T>>
) {

    fun loadData(cacheKey: String, maxTimeInCache: Long): T? {

        val cacheItem = lruCache.get(cacheKey) ?: return null

        val dataIsNotExpired = System.currentTimeMillis() - cacheItem.creationDate <= maxTimeInCache
        return if (dataIsNotExpired) {
            cacheItem.data
        } else {
            lruCache.remove(cacheKey)
            null
        }
    }

    fun saveData(data: T, cacheKey: String) {
        lruCache.put(cacheKey, CacheItem(data))
    }

    fun removeData(cacheKey: String) = lruCache.remove(cacheKey) != null

    fun removeAllData() {
        lruCache.evictAll()
    }
}