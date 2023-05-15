package com.usacheow.coredata.storage.cacheprovider

import io.ktor.util.reflect.TypeInfo
import kotlin.time.Duration

interface CacheProvider : CacheCleaner {

    suspend fun <T> get(typeInfo: TypeInfo, key: String): T?

    suspend fun <T : Any> save(typeInfo: TypeInfo, key: String, data: T, lifeDuration: Duration)
}

interface CacheCleaner {

    suspend fun clear(typeInfo: TypeInfo, key: String)

    suspend fun clearAll()
}

class CacheElement<T>(
    val data: T,
    private val lifeDuration: Duration,
    private val creationDate: Long = System.currentTimeMillis(),
) {

    suspend fun getOrClear(clearAction: suspend () -> Unit): CacheElement<T>? {
        val isExpired = System.currentTimeMillis() - creationDate > lifeDuration.inWholeMilliseconds
        return if (isExpired) {
            clearAction()
            null
        } else {
            this
        }
    }
}