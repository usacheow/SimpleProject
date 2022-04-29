package com.usacheow.coredata.network

import com.usacheow.corecommon.AppError
import com.usacheow.corecommon.Completable
import com.usacheow.corecommon.Effect
import com.usacheow.coredata.cache.CacheProvider
import com.usacheow.coredata.json.KotlinxSerializationJsonProvider
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalStdlibApi::class, ExperimentalTime::class)
suspend inline fun <reified T : Any> cachedApiCall(
    key: String,
    cacheProvider: CacheProvider,
    dispatcher: CoroutineDispatcher,
    needActualData: Boolean = false,
    lifeDuration: Duration = 5.minutes,
    noinline request: suspend () -> Response<T>,
): Effect<T> {
    return if (needActualData) {
        apiCall(dispatcher, request)
            .doOnSuccess { cacheProvider.save(typeOf<T>(), key, it, lifeDuration) }
            .applyCacheData { cacheProvider.get(typeOf<T>(), key) }
    } else {
        cacheProvider.get<T>(typeOf<T>(), key)
            ?.let { Effect.success(it) }
            ?: apiCall(dispatcher, request)
                .doOnSuccess { cacheProvider.save(typeOf<T>(), key, it, lifeDuration) }
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
            else -> Effect.error(AppError.EmptyResponse())
        }
        else -> Effect.success(body)
    }

    else -> {
        val exception = errorBody()?.let { errorBody ->
            val error = KotlinxSerializationJsonProvider().get().decodeFromStream<ErrorDto>(errorBody.byteStream())
            AppError.Server(error.message)
        } ?: AppError.Server()

        Effect.error(exception)
    }
}

fun Throwable.toApiError() = when (this) {
    is UnknownHostException,
    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> AppError.Host()

    is CancellationException -> AppError.Coroutine()

    is HttpException -> when (response()?.code()) {
        HttpURLConnection.HTTP_BAD_REQUEST,
        HttpURLConnection.HTTP_FORBIDDEN,
        HttpURLConnection.HTTP_NOT_FOUND -> AppError.Host(cause = this)

        HttpURLConnection.HTTP_UNAVAILABLE,
        HttpURLConnection.HTTP_UNAUTHORIZED -> AppError.InvalidAccessToken()

        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> AppError.Server()

        else -> AppError.Unknown()
    }

    else -> AppError.Unknown()
}