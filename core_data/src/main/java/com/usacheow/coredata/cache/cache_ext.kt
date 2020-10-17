package com.usacheow.coredata.cache

import com.usacheow.coredata.cache.base.CacheProvider
import com.usacheow.coredata.network.Effect
import com.usacheow.coredata.network.apiCall
import com.usacheow.coredata.network.ifError
import com.usacheow.coredata.network.ifSuccess
import retrofit2.Response

suspend inline fun <reified T : Any> takeCacheOrRefresh(
    noinline request: suspend () -> Response<T>,
    cacheProvider: CacheProvider,
    key: String,
    timeInMillis: Long = 1 * 60 * 1000
) = cacheProvider.get(T::class.java, key, timeInMillis)?.let {
    Effect.Success(it)
} ?: apiCall(request).ifSuccess {
    cacheProvider.save(data, key)
}.ifError {
    cacheProvider.clear(T::class.java, key)
}