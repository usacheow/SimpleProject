package com.usacheow.coredata.cache

import androidx.collection.LruCache
import java.util.Collections
import javax.inject.Inject

private const val DEFAULT_SIZE_CACHE_BYTES = 4 * 1024 * 1024

class LruCacheProvider @Inject constructor() : CacheProvider {

    private val persisterMap = Collections.synchronizedMap(
        mutableMapOf<Class<*>, LruCachePersister<*>>()
    )

    override suspend fun <T> get(clazz: Class<T>, key: String): T? {
        return getPersister(clazz)
            .get(key)
            ?.getOrClear { clear(clazz, key) }
            ?.data
    }

    override suspend fun <T : Any> save(key: String, data: T, lifeTimeInMillis: Long) {
        val cache = CacheElement(data, lifeTimeInMillis)
        getPersister(data.javaClass).put(key, cache)
    }

    override suspend fun <T> clear(clazz: Class<T>, key: String) {
        persisterMap[clazz]?.remove(key)
    }

    override suspend fun clearAll() {
        persisterMap.entries.forEach { it.value.evictAll() }
    }

    private fun <T> getPersister(clazz: Class<T>) = persisterMap.getOrPut(clazz) {
        LruCachePersister<T>()
    } as LruCachePersister<T>
}

class LruCachePersister<T> : LruCache<String, CacheElement<T>>(DEFAULT_SIZE_CACHE_BYTES)