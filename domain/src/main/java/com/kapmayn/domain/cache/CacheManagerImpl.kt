package com.kapmayn.domain.cache

import androidx.collection.LruCache
import com.kapmayn.core.domain.cache.CacheManager
import com.kapmayn.data.models.Stub
import java.util.*
import javax.inject.Inject

private const val DEFAULT_SIZE_CACHE = 16

class CacheManagerImpl
@Inject constructor() : CacheManager {

    private val mapPersister = Collections.synchronizedMap(mutableMapOf<Class<*>, LruCachePersister<*>>())

    init {
        addPersister(String::class.java, 1024 * 1024)
        addPersister(Int::class.java, 1024)
    }

    private fun <T> addPersister(clazz: Class<T>, maxSize: Int) {
        mapPersister[clazz] = LruCachePersister<T>(LruCache(maxSize))
    }

    override fun <T> loadData(resultClass: Class<T>, key: String, memoryCacheTime: Long): T? {
        return getObjectPersister(resultClass).loadData(key, memoryCacheTime)
    }

    override fun <T : Any> saveData(data: T, key: String) {
        getObjectPersister(data.javaClass).saveData(data, key)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getObjectPersister(clazz: Class<T>): LruCachePersister<T> {
        if (!mapPersister.containsKey(clazz)) {
            addPersister(clazz, DEFAULT_SIZE_CACHE)
        }
        return mapPersister[clazz] as LruCachePersister<T>
    }

    override fun <T> clearCache(type: Class<T>) {
        mapPersister[type]?.removeAllData()
    }

    override fun <T> clearCache(type: Class<T>, cacheKey: String) {
        mapPersister[type]?.removeData(cacheKey)
    }

    override fun clearAllCache() {
        mapPersister.entries.forEach { entry ->
            entry.value.removeAllData()
        }
    }

    override fun clearStubs() {
        clearCache(Stub::class.java)
    }
}