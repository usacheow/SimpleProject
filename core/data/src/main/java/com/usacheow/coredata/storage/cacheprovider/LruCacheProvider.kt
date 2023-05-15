package com.usacheow.coredata.storage.cacheprovider

import androidx.collection.LruCache
import io.ktor.util.reflect.TypeInfo
import java.util.Collections
import javax.inject.Inject
import kotlin.time.Duration

private const val DEFAULT_SIZE_CACHE_BYTES = 4 * 1024 * 1024

class LruCacheProvider @Inject constructor() : CacheProvider {

    private val persisterMap = Collections.synchronizedMap(
        mutableMapOf<TypeInfo, LruCachePersister<*>>()
    )

    override suspend fun <T> get(typeInfo: TypeInfo, key: String): T? {
        return getPersister<T>(typeInfo)
            .get(key)
            ?.getOrClear { clear(typeInfo, key) }
            ?.data
    }

    override suspend fun <T : Any> save(typeInfo: TypeInfo, key: String, data: T, lifeDuration: Duration) {
        val cache = CacheElement(data, lifeDuration)
        getPersister<T>(typeInfo).put(key, cache)
    }

    override suspend fun clear(typeInfo: TypeInfo, key: String) {
        persisterMap[typeInfo]?.remove(key)
    }

    override suspend fun clearAll() {
        persisterMap.entries.forEach { it.value.evictAll() }
    }

    private fun <T> getPersister(typeInfo: TypeInfo) = persisterMap.getOrPut(typeInfo) {
        LruCachePersister<T>()
    } as LruCachePersister<T>
}

class LruCachePersister<T> : LruCache<String, CacheElement<T>>(DEFAULT_SIZE_CACHE_BYTES)