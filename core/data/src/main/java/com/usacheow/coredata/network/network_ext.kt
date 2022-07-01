package com.usacheow.coredata.network

import com.usacheow.corecommon.model.AppError
import com.usacheow.corecommon.model.Completable
import com.usacheow.corecommon.model.Effect
import com.usacheow.coredata.cache.CacheProvider
import com.usacheow.coredata.json.KotlinxSerializationJsonProvider
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import retrofit2.Response

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
            else -> Effect.error(AppError.Unknown())
        }
        else -> Effect.success(body)
    }

    else -> {
        val errorBody = errorBody()?.let {
            KotlinxSerializationJsonProvider().get().decodeFromStream<ErrorDto>(it.byteStream())
        }

        val error = when {
            errorBody == null -> AppError.Unknown()

            else -> AppError.Custom(
                message = errorBody.message,
                displayMessage = errorBody.message,
                code = code(),
            )
        }

        Effect.error(error)
    }
}

fun Throwable.toApiError() = when (this) {
    is UnknownHostException,
    is SSLException,
    is ConnectException,
    is SocketTimeoutException -> AppError.Custom(cause = this as Exception)

    is CancellationException -> throw this

    else -> AppError.Unknown()
}