package com.usacheow.coredata.cache

import androidx.collection.LruCache
import java.util.Collections
import javax.inject.Inject
import kotlin.reflect.KType
import kotlin.time.Duration

private const val DEFAULT_SIZE_CACHE_BYTES = 4 * 1024 * 1024

class LruCacheProvider @Inject constructor() : CacheProvider {

    private val persisterMap = Collections.synchronizedMap(
        mutableMapOf<KType, LruCachePersister<*>>()
    )

    override suspend fun <T> get(type: KType, key: String): T? {
        return getPersister<T>(type)
            .get(key)
            ?.getOrClear { clear(type, key) }
            ?.data
    }

    override suspend fun <T : Any> save(type: KType, key: String, data: T, lifeDuration: Duration) {
        val cache = CacheElement(data, lifeDuration)
        getPersister<T>(type).put(key, cache)
    }

    override suspend fun clear(type: KType, key: String) {
        persisterMap[type]?.remove(key)
    }

    override suspend fun clearAll() {
        persisterMap.entries.forEach { it.value.evictAll() }
    }

    private fun <T> getPersister(type: KType) = persisterMap.getOrPut(type) {
        LruCachePersister<T>()
    } as LruCachePersister<T>
}

class LruCachePersister<T> : LruCache<String, CacheElement<T>>(DEFAULT_SIZE_CACHE_BYTES)