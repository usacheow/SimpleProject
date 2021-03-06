package com.usacheow.coredata.network

import com.google.gson.Gson
import com.usacheow.coredata.cache.base.CacheProvider
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
    timeInMillis: Long = 1 * 60 * 1000,
    noinline request: suspend () -> Response<T>,
) = cacheProvider
    .get(T::class.java, key, timeInMillis)
    ?.let { Effect.Success(it) }
    ?: apiCall(dispatcher, request)
        .doOnSuccess { cacheProvider.save(data, key) }
        .doOnError { cacheProvider.clear(T::class.java, key) }

suspend fun <T : Any> apiCall(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> Response<T>
): Effect<T> = withContext(dispatcher) {
    try {
        block().handle()
    } catch (t: Throwable) {
        Effect.Error(t.toError())
    }
}

private fun <T : Any> Response<T>.handle() = when {
    isSuccessful -> body()?.let {
        Effect.Success(it)
    } ?: Effect.Error(ApiError.EmptyResponseException())

    else -> {
        val exception = errorBody()?.let { errorBody ->
            val error = Gson().fromJson(errorBody.charStream(), ErrorMessage::class.java)
            ApiError.ServerException(error.message)
        } ?: ApiError.UnknownException()

        Effect.Error(exception)
    }
}

private fun Throwable.toError() = when (this) {
    is UnknownHostException -> ApiError.HostException()

    is HttpException -> when (val code = response()?.code()) {
        HttpURLConnection.HTTP_BAD_REQUEST,
        HttpURLConnection.HTTP_FORBIDDEN,
        HttpURLConnection.HTTP_NOT_FOUND -> ApiError.ApiException(responseCode = code, cause = this)

        HttpURLConnection.HTTP_UNAVAILABLE,
        HttpURLConnection.HTTP_UNAUTHORIZED -> ApiError.InvalidAccessTokenException()

        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ApiError.HostException()

        else -> ApiError.UnknownException()
    }

    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> ApiError.HostException()

    is CancellationException -> ApiError.CoroutineException()

    else -> ApiError.UnknownException()
}