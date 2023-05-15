package com.usacheow.coredata.network

import com.usacheow.corecommon.ext.logError
import com.usacheow.corecommon.model.AppError
import com.usacheow.corecommon.model.Completable
import com.usacheow.corecommon.model.Effect
import com.usacheow.coredata.storage.cacheprovider.CacheProvider
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
import kotlin.reflect.KType

abstract class Network {

    suspend inline fun <reified T : Any> cachedApiCall(
        key: String,
        cacheProvider: CacheProvider,
        dispatcher: CoroutineDispatcher,
        needActualData: Boolean = false,
        lifeDuration: Duration = 5.minutes,
        noinline request: suspend () -> Response<T>,
    ) = cachedApiCall(typeOf<T>(), key, cacheProvider, dispatcher, needActualData, lifeDuration, request)

    abstract suspend fun <T : Any> cachedApiCall(
        type: KType,
        key: String,
        cacheProvider: CacheProvider,
        dispatcher: CoroutineDispatcher,
        needActualData: Boolean = false,
        lifeDuration: Duration = 5.minutes,
        request: suspend () -> Response<T>,
    ): Effect<T>

    suspend inline fun <reified T : Any> apiCall(
        dispatcher: CoroutineDispatcher,
        noinline block: suspend () -> Response<T>,
    ) = apiCall(typeOf<T>(), dispatcher, block)

    abstract suspend fun <T : Any> apiCall(
        type: KType,
        dispatcher: CoroutineDispatcher,
        block: suspend () -> Response<T>,
    ): Effect<T>
}

internal class NetworkImpl : Network() {

    override suspend fun <T : Any> cachedApiCall(
        type: KType,
        key: String,
        cacheProvider: CacheProvider,
        dispatcher: CoroutineDispatcher,
        needActualData: Boolean,
        lifeDuration: Duration,
        request: suspend () -> Response<T>,
    ): Effect<T> {
        return if (needActualData) {
            apiCall(type, dispatcher, request)
                .doOnSuccess { cacheProvider.save(type, key, it, lifeDuration) }
                .applyCacheData { cacheProvider.get(type, key) }
        } else {
            cacheProvider.get<T>(type, key)
                ?.let { Effect.success(it) }
                ?: apiCall(type, dispatcher, request)
                    .doOnSuccess { cacheProvider.save(type, key, it, lifeDuration) }
        }
    }

    override suspend fun <T : Any> apiCall(
        type: KType,
        dispatcher: CoroutineDispatcher,
        block: suspend () -> Response<T>,
    ): Effect<T> = withContext(dispatcher) {
        try {
            block().toEffect(type)
        } catch (t: Throwable) {
            logError(t.message.orEmpty())
            Effect.error(t.toApiError())
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun <T : Any> Response<T>.toEffect(type: KType) = when {
        isSuccessful -> when (val body = body()) {
            null -> when (type) {
                typeOf<Completable>() -> Effect.success(Completable as T)
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

    private fun Throwable.toApiError() = when (this) {
        is UnknownHostException,
        is SSLException,
        is ConnectException,
        is SocketTimeoutException -> AppError.Custom(cause = this as Exception)

        is CancellationException -> throw this

        else -> AppError.Unknown()
    }
}