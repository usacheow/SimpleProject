package com.usacheow.coredata.network

import com.google.gson.Gson
import com.usacheow.coredata.cache.base.CacheProvider
import com.usacheow.coredata.gson.fromJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException

suspend inline fun <reified T : Any> cachedApiCall(
    key: String,
    cacheProvider: CacheProvider,
    dispatcher: CoroutineDispatcher,
    needActualData: Boolean = false,
    timeInMillis: Long = 1 * 60 * 1000,
    noinline request: suspend () -> Response<T>,
): Effect2<T> {
    return if (needActualData) {
        apiCall(dispatcher, request)
            .doOnSuccess { cacheProvider.save(it, key) }
            .applyCacheData { cacheProvider.get(T::class.java, key, timeInMillis) }
    } else {
        cacheProvider.get(T::class.java, key, timeInMillis)
            ?.let { Effect2.success(it) }
            ?: apiCall(dispatcher, request)
                .doOnSuccess { cacheProvider.save(it, key) }
    }
}

suspend fun <T : Any> apiCall(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> Response<T>,
): Effect2<T> = withContext(dispatcher) {
    try {
        block().handle()
    } catch (t: Throwable) {
        Effect2.error(t.toError())
    }
}

private fun <T : Any> Response<T>.handle() = when {
    isSuccessful -> body()?.let {
        Effect2.success(it)
    } ?: Effect2.error(ApiError.EmptyResponseException())

    else -> {
        val exception = errorBody()?.let { errorBody ->
            val error = Gson().fromJson<ErrorDto>(errorBody.charStream())
            ApiError.ServerException(error.message)
        } ?: ApiError.ServerException()

        Effect2.error(exception)
    }
}

private fun Throwable.toError() = when (this) {
    is UnknownHostException,
    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> ApiError.HostException()

    is CancellationException -> ApiError.CoroutineException()

    is HttpException -> when (val code = response()?.code()) {
        HttpURLConnection.HTTP_BAD_REQUEST,
        HttpURLConnection.HTTP_FORBIDDEN,
        HttpURLConnection.HTTP_NOT_FOUND -> ApiError.HostException(cause = this)

        HttpURLConnection.HTTP_UNAVAILABLE,
        HttpURLConnection.HTTP_UNAUTHORIZED -> ApiError.InvalidAccessTokenException()

        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ApiError.ServerException()

        else -> ApiError.UnknownException()
    }

    else -> ApiError.UnknownException()
}