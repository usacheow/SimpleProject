package com.usacheow.coredata.cache

interface CacheProvider : CacheCleaner {

    suspend fun <T> get(clazz: Class<T>, key: String): T?

    suspend fun <T : Any> save(key: String, data: T, lifeTimeInMillis: Long)
}

class CacheElement<T>(
    val data: T,
    private val lifeTimeInMillis: Long,
    private val creationDate: Long = System.currentTimeMillis(),
) {

    suspend fun getOrClear(clearAction: suspend () -> Unit): CacheElement<T>? {
        val isExpired = System.currentTimeMillis() - creationDate > lifeTimeInMillis
        return if (isExpired) {
            clearAction()
            null
        } else {
            this
        }
    }
}