package com.usacheow.coredata.cache.lrucache

import com.usacheow.coredata.cache.base.CacheProvider
import java.util.Collections
import javax.inject.Inject

private const val DEFAULT_SIZE_CACHE = 16

class LruCacheProvider
@Inject constructor() : CacheProvider {

    private val persisterMap = Collections.synchronizedMap(mutableMapOf<Class<*>, LruCachePersister<*>>())

    init {
        addPersister(String::class.java, 1024 * 1024)
        addPersister(Int::class.java, 1024)
    }

    override fun <T> get(clazz: Class<T>, key: String, memoryCacheTime: Long): T? {
        return getPersister(clazz).get(key, memoryCacheTime)
    }

    override fun <T : Any> save(data: T, key: String) {
        getPersister(data.javaClass).save(data, key)
    }

    override fun <T> clear(type: Class<T>) {
        persisterMap[type]?.clearAll()
    }

    override fun <T> clear(type: Class<T>, cacheKey: String) {
        persisterMap[type]?.clear(cacheKey)
    }

    override fun clearAll() {
        persisterMap.entries.forEach { entry ->
            entry.value.clearAll()
        }
    }

    private fun <T> getPersister(clazz: Class<T>): LruCachePersister<T> {
        if (!persisterMap.containsKey(clazz)) {
            addPersister(clazz, DEFAULT_SIZE_CACHE)
        }
        return persisterMap[clazz] as LruCachePersister<T>
    }

    private fun <T> addPersister(clazz: Class<T>, maxSize: Int) {
        persisterMap[clazz] = LruCachePersister<T>(maxSize)
    }
}