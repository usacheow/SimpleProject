package com.usacheow.coredata.cache

import com.usacheow.coredata.cache.base.CacheProvider
import io.reactivex.Single

class CacheLoadingException : Exception("Don't cache")

inline fun <reified RESULT> Single<RESULT>.takeCacheOrRefresh(
    cacheProvider: CacheProvider,
    key: String,
    timeInMillis: Long = 1 * 60 * 1000
) = Single.fromCallable {
    cacheProvider.get(RESULT::class.java, key, timeInMillis) ?: throw CacheLoadingException()
}
    .onErrorResumeNext { this }
    .doAfterSuccess { cacheProvider.save(it as Any, key) }