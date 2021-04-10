package com.usacheow.coredata.cache

import com.usacheow.coredata.cache.base.CacheProvider
import com.usacheow.coredata.network.Effect
import com.usacheow.coredata.network.apiCall
import com.usacheow.coredata.network.doOnError
import com.usacheow.coredata.network.doOnSuccess
import retrofit2.Response

inline fun <reified T : Any> takeCacheOrRefresh(
    request: () -> Response<T>,
    cacheProvider: CacheProvider,
    key: String,
    timeInMillis: Long = 1 * 60 * 1000
) = cacheProvider.get(T::class.java, key, timeInMillis)?.let {
    Effect.Success(it)
} ?: apiCall(request).doOnSuccess {
    cacheProvider.save(data, key)
}.doOnError {
    cacheProvider.clear(T::class.java, key)
}