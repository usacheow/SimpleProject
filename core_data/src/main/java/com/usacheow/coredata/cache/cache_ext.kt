package com.usacheow.coredata.cache

import com.usacheow.coredata.cache.base.CacheManager
import io.reactivex.Single

inline fun <reified RESULT> Single<RESULT>.takeCacheOrRefresh(
    cacheManager: CacheManager,
    key: String,
    timeInMillis: Long = 1 * 60 * 1000
) = Single.fromCallable {
    cacheManager.loadData(RESULT::class.java, key, timeInMillis) ?: throw CacheLoadingException()
}
    .onErrorResumeNext { this }
    .doAfterSuccess { cacheManager.saveData(it as Any, key) }