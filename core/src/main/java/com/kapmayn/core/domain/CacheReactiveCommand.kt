package com.kapmayn.core.domain

import com.kapmayn.core.domain.cache.CacheCleaner
import com.kapmayn.core.domain.cache.CacheLoadingException
import com.kapmayn.core.domain.cache.CacheManager
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class CacheReactiveCommand<RESULT>
constructor(
    private val resultClass: Class<RESULT>,
    private val cacheManager: CacheManager
) {

    open fun execute(): Single<RESULT> {
        val singleResult = Single
            .fromCallable {
                val cacheResult = cacheManager.loadData(resultClass, getCacheKey(), getMemoryCacheTime())
                return@fromCallable cacheResult ?: throw CacheLoadingException()
            }
            .onErrorResumeNext { return@onErrorResumeNext run() }
        return singleResult
            .doAfterSuccess { newResult ->
                cacheManager.saveData(newResult as Any, getCacheKey())
                cleanCache(cacheManager)
            }
            .subscribeOn(Schedulers.io())
    }

    abstract fun run(): Single<RESULT>

    abstract fun getCacheKey(): String

    abstract fun getMemoryCacheTime(): Long

    open fun cleanCache(cacheCleaner: CacheCleaner) {}
}