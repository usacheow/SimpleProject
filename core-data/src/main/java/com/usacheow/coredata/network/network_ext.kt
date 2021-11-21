package com.usacheow.coredata.network

import com.usacheow.core.Completable
import com.usacheow.core.Effect
import com.usacheow.coredata.cache.CacheProvider
import com.usacheow.coredata.json.KotlinxSerializationJsonProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
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
    timeInMinutes: Int = 5,
    noinline request: suspend () -> Response<T>,
): Effect<T> {
    val memoryCacheTimeInMilliseconds = timeInMinutes * 60 * 1000L
    return if (needActualData) {
        apiCall(dispatcher, request)
            .doOnSuccess { cacheProvider.save(it, key) }
            .applyCacheData { cacheProvider.get(T::class.java, key, memoryCacheTimeInMilliseconds) }
    } else {
        cacheProvider.get(T::class.java, key, memoryCacheTimeInMilliseconds)
            ?.let { Effect.success(it) }
            ?: apiCall(dispatcher, request)
                .doOnSuccess { cacheProvider.save(it, key) }
    }
}

suspend inline fun <reified T : Any> apiCall(
    dispatcher: CoroutineDispatcher,
    noinline block: suspend () -> Response<T>,
): Effect<T> = withContext(dispatcher) {
    try {
        block().toEffect()
    } catch (t: Throwable) {
        Effect.error(t.toApiError())
    }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T : Any> Response<T>.toEffect() = when {
    isSuccessful -> when (val body = body()) {
        null -> when (T::class) {
            Completable::class -> Effect.success(Completable as T)
            else -> Effect.error(ApiError.EmptyResponseException())
        }
        else -> Effect.success(body)
    }

    else -> {
        val exception = errorBody()?.let { errorBody ->
            val error = KotlinxSerializationJsonProvider().get().decodeFromStream<ErrorDto>(errorBody.byteStream())
            ApiError.ServerException(error.message)
        } ?: ApiError.ServerException()

        Effect.error(exception)
    }
}

fun Throwable.toApiError() = when (this) {
    is UnknownHostException,
    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> ApiError.HostException()

    is CancellationException -> ApiError.CoroutineException()

    is HttpException -> when (response()?.code()) {
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