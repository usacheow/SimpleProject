package com.usacheow.coredata.network

import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import javax.net.ssl.SSLException

object Completable

sealed class Effect<out T : Any> {

    data class Success<out T : Any>(val data: T) : Effect<T>()

    data class Error(val exception: ApiError) : Effect<Nothing>()
}

fun Effect<*>.toCompletableResult() = when (this) {
    is Effect.Success<*> -> Effect.Success(Completable)

    is Effect.Error -> when (exception) {
        is ApiError.EmptyResponseException -> Effect.Success(Completable)

        else -> this
    }
}

suspend fun <T : Any> Effect<T>.mapWhenSuccess(block: suspend Effect.Success<T>.() -> Effect<T>): Effect<T> = try {
    when (this) {
        is Effect.Success<T> -> this.block()

        else -> this
    }
} catch (e: Exception) {
    Effect.Error(ApiError.DataMappingException())
}

suspend fun <T : Any> Effect<T>.mapWhenError(block: suspend Effect.Error.() -> Effect<T>): Effect<T> = try {
    when (this) {
        is Effect.Error -> this.block()

        else -> this
    }
} catch (e: Exception) {
    Effect.Error(ApiError.DataMappingException())
}

suspend fun <T : Any> Effect<T>.doOnSuccess(block: suspend Effect.Success<T>.() -> Unit): Effect<T> {
    if (this is Effect.Success<T>) {
        this.block()
    }

    return this
}

suspend fun <T : Any> Effect<T>.doOnError(block: suspend Effect.Error.() -> Unit): Effect<T> {
    if (this is Effect.Error) {
        this.block()
    }

    return this
}

suspend fun <T : Any> apiCall(block: suspend () -> Response<T>): Effect<T> = try {
    block.invoke().process()
} catch (t: Throwable) {
    Effect.Error(t.toError())
}

private fun <T : Any> Response<T>.process() = when {
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