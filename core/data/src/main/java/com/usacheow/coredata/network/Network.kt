package com.usacheow.coredata.network

import com.usacheow.corecommon.ext.logError
import com.usacheow.corecommon.model.AppError
import com.usacheow.corecommon.model.BuildInfo
import com.usacheow.corecommon.model.Completable
import com.usacheow.corecommon.model.Effect
import com.usacheow.coredata.network.json.KotlinxSerializationJsonProvider
import com.usacheow.coredata.storage.preferences.TokenStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException
import kotlin.reflect.KClass

abstract class Network {

    abstract suspend fun <T : Any> call(
        typeInfo: TypeInfo,
        dispatcher: CoroutineDispatcher,
        tags: Map<KClass<*>, Tag>,
        requestBuilder: HttpRequestBuilder.() -> Unit,
    ): Effect<T>

    inline fun <reified T : Any> builder() = RequestBuilder<T>(typeInfo<T>())

    inner class RequestBuilder<T : Any> constructor(private val typeInfo: TypeInfo) {

        private val tags = mutableMapOf<KClass<*>, Tag>()
        private var dispatcher: CoroutineDispatcher = Dispatchers.IO

        fun dispatcher(dispatcher: CoroutineDispatcher) = apply { this.dispatcher = dispatcher }

        fun tag(tag: Tag) = apply { tags[tag::class] = tag }

        suspend fun request(block: HttpRequestBuilder.() -> Unit): Effect<T> {
            return call(typeInfo, dispatcher, tags, block)
        }
    }
}

class NetworkImpl(
    private val jsonProvider: KotlinxSerializationJsonProvider,
    private val tokenStorage: TokenStorage,
    private val buildInfo: BuildInfo,
) : Network() {

    override suspend fun <T : Any> call(
        typeInfo: TypeInfo,
        dispatcher: CoroutineDispatcher,
        tags: Map<KClass<*>, Tag>,
        requestBuilder: HttpRequestBuilder.() -> Unit,
    ): Effect<T> {
        val cachedRequestTag = tags[CachedRequestTag::class] as? CachedRequestTag?
        return when {
            cachedRequestTag == null -> request(typeInfo, dispatcher, tags, requestBuilder)

            cachedRequestTag.needActual -> request<T>(typeInfo, dispatcher, tags, requestBuilder)
                .doOnSuccess { saveToCache(it, typeInfo, cachedRequestTag) }
                .applyCacheData { getFromCache(typeInfo, cachedRequestTag) }

            else -> getFromCache<T>(typeInfo, cachedRequestTag)
                ?.let { Effect.success(it) }
                ?: request<T>(typeInfo, dispatcher, tags, requestBuilder)
                    .doOnSuccess { saveToCache(it, typeInfo, cachedRequestTag) }
        }
    }

    private suspend fun <T : Any> getFromCache(
        typeInfo: TypeInfo,
        cachedRequestTag: CachedRequestTag,
    ) = cachedRequestTag.cacheProvider.get<T>(typeInfo, cachedRequestTag.key)

    private suspend fun <T : Any> saveToCache(
        data: T,
        typeInfo: TypeInfo,
        cachedRequestTag: CachedRequestTag,
    ) = cachedRequestTag.cacheProvider.save(
        typeInfo,
        cachedRequestTag.key,
        data,
        cachedRequestTag.lifeDuration
    )

    private suspend fun <T : Any> request(
        typeInfo: TypeInfo,
        dispatcher: CoroutineDispatcher,
        tags: Map<KClass<*>, Tag>,
        requestBuilder: HttpRequestBuilder.() -> Unit,
    ): Effect<T> = withContext(dispatcher) {
        try {
            createHttpClient(tags).request(requestBuilder).toEffect(typeInfo)
        } catch (e: CancellationException) {
            throw e
        } catch (t: Throwable) {
            logError(t.message.orEmpty())
            Effect.error(t.toApiError())
        }
    }

    private suspend fun <T : Any> HttpResponse.toEffect(
        typeInfo: TypeInfo,
    ): Effect<T> = when (status.value) {
        in 200..299 -> when (val body = body<T?>(typeInfo)) {
            null -> when (typeInfo) {
                typeInfo<Completable>() -> Effect.success(Completable as T)
                else -> Effect.error(AppError.Unknown())
            }

            else -> Effect.success(body)
        }

        else -> {
            val error = when (val errorBody = body<ErrorDto?>()) {
                null -> AppError.Unknown()
                else -> AppError.Custom(
                    message = errorBody.message,
                    displayMessage = errorBody.message,
                    code = status.value,
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

    private fun createHttpClient(tags: Map<KClass<*>, Tag>) = HttpClient(Android) {
        defaultRequest {
            if (tags.containsKey(AuthRequestTag::class)) {
                header(HttpHeaders.Authorization, "Bearer ${tokenStorage.decodedAccessToken}")
            }
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 20_000
            requestTimeoutMillis = 20_000
        }
        install(ContentNegotiation) {
            json(jsonProvider.get())
        }
        if (buildInfo.isDebug) install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }
}

@Serializable
data class ErrorDto(
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?
)