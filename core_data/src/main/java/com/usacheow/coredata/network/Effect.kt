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

inline fun <IN : Any, OUT : Any> Effect<IN>.mapEffect(
    onSuccess: Effect.Success<IN>.() -> OUT,
    noinline onError: Effect.Error.() -> OUT,
): OUT = when (this) {
    is Effect.Success<IN> -> this.onSuccess()

    is Effect.Error -> this.onError()
}

inline fun <IN : Any, OUT : Any> Effect<IN>.mapSuccessEffect(
    block: Effect.Success<IN>.() -> Effect<OUT>,
): Effect<OUT> = when (this) {
    is Effect.Success<IN> -> this.block()

    is Effect.Error -> Effect.Error(exception)
}

inline fun <IN : Any> Effect<IN>.mapErrorEffect(block: Effect.Error.() -> Effect<IN>): Effect<IN> = when (this) {
    is Effect.Error -> this.block()

    is Effect.Success<IN> -> this
}

inline fun <IN : Any, OUT : Any> Effect<IN>.mapSuccessEffectData(block: IN.() -> OUT): Effect<OUT> = when (this) {
    is Effect.Success<IN> -> Effect.Success(this.data.block())

    is Effect.Error -> Effect.Error(exception)
}

inline fun <IN : Any> Effect<IN>.doOnSuccess(block: Effect.Success<IN>.() -> Unit): Effect<IN> {
    if (this is Effect.Success<IN>) {
        this.block()
    }

    return this
}

inline fun <IN : Any> Effect<IN>.doOnError(block: Effect.Error.() -> Unit): Effect<IN> {
    if (this is Effect.Error) {
        this.block()
    }

    return this
}

fun <IN : Any> Effect<IN>.getDataIfSuccess(): IN? {
    if (this is Effect.Success<IN>) {
        return data
    }

    return null
}

fun <IN : Any> IN.toSuccessEffect(): Effect.Success<IN> {
    return Effect.Success(this)
}