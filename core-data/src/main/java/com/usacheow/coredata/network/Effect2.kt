package com.usacheow.coredata.network

object Completable

sealed class State<out T : Any>

class Loading<T : Any>(val data: T? = null) : State<T>()

class Effect2<DATA : Any> private constructor(
    private val value: Data<DATA>,
) : State<DATA>() {

    companion object {

        fun <T : Any> success(data: T) = Effect2(Success(data))

        fun <T : Any> error(
            exception: Throwable,
            cachedData: T? = null,
        ) = Effect2(Error(exception, cachedData))
    }

    val isSuccess: Boolean get() = value is Success<DATA>

    val isError: Boolean get() = value is Error<DATA>

    val dataIfSuccess
        get() = when (value) {
            is Success<DATA> -> value.data
            else -> null
        }

    val dataOrNull
        get() = when (value) {
            is Success<DATA> -> value.data
            is Error<DATA> -> value.data
        }

    val exceptionOrNull
        get() = when (value) {
            is Error<DATA> -> value.exception
            else -> null
        }

    suspend fun doOnSuccess(
        block: suspend (data: DATA) -> Unit,
    ): Effect2<DATA> {
        if (value is Success<DATA>) {
            block(value.data)
        }
        return this
    }

    suspend fun doOnError(
        block: suspend (exception: Throwable, data: DATA?) -> Unit,
    ): Effect2<DATA> {
        if (value is Error<DATA>) {
            block(value.exception, value.data)
        }
        return this
    }

    suspend fun applyCacheData(
        cachedDataProvider: suspend () -> DATA?,
    ): Effect2<DATA> = when (value) {
        is Success<DATA> -> this
        is Error<DATA> -> error(value.exception, cachedDataProvider())
    }

    suspend fun <OUT : Any> map(
        transform: suspend (value: DATA) -> OUT,
    ): Effect2<OUT> = when (value) {
        is Success<DATA> -> success(transform(value.data))
        is Error<DATA> -> error(value.exception, value.data?.let { transform(it) })
    }

    fun toCompletable() = when (value) {
        is Success<DATA> -> success(Completable)

        is Error<DATA> -> when (value.exception) {
            is ApiError.EmptyResponseException -> success(Completable)

            else -> error(value.exception)
        }
    }

    internal sealed class Data<DATA : Any>

    internal data class Success<DATA : Any>(
        val data: DATA,
    ) : Data<DATA>()

    internal data class Error<DATA : Any>(
        val exception: Throwable,
        val data: DATA? = null,
    ) : Data<DATA>()
}

inline fun <IN, OUT : Any> IN.tryRun(block: IN.() -> OUT): Effect2<OUT> {
    return try {
        Effect2.success(block())
    } catch (e: Throwable) {
        Effect2.error(e)
    }
}