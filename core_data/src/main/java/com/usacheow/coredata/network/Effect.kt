package com.usacheow.coredata.network

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

inline fun <T : Any, R : Any> Effect<T>.mapWhenSuccess(block: Effect.Success<T>.() -> Effect<R>): Effect<R> = try {
    when (this) {
        is Effect.Success<T> -> this.block()

        is Effect.Error -> Effect.Error(exception)
    }
} catch (e: Exception) {
    Effect.Error(ApiError.DataMappingException())
}

inline fun <T : Any> Effect<T>.mapWhenError(block: Effect.Error.() -> Effect<T>): Effect<T> = try {
    when (this) {
        is Effect.Error -> this.block()

        is Effect.Success<T> -> this
    }
} catch (e: Exception) {
    Effect.Error(ApiError.DataMappingException())
}

inline fun <T : Any, R : Any> Effect<T>.changeDataWhenSuccess(block: T.() -> R): Effect<R> = try {
    when (this) {
        is Effect.Success<T> -> Effect.Success(this.data.block())

        is Effect.Error -> Effect.Error(exception)
    }
} catch (e: Exception) {
    Effect.Error(ApiError.DataMappingException())
}

inline fun <T : Any> Effect<T>.doOnSuccess(block: Effect.Success<T>.() -> Unit): Effect<T> {
    if (this is Effect.Success<T>) {
        this.block()
    }

    return this
}

inline fun <T : Any> Effect<T>.doOnError(block: Effect.Error.() -> Unit): Effect<T> {
    if (this is Effect.Error) {
        this.block()
    }

    return this
}

fun <T : Any> Effect<T>.getDataIfSuccess(): T? {
    if (this is Effect.Success<T>) {
        return data
    }

    return null
}

fun <T : Any> T.toSuccessEffect(): Effect.Success<T> {
    return Effect.Success(this)
}