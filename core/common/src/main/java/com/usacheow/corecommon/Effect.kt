package com.usacheow.corecommon

object Completable

sealed class State<out T : Any>

class Loading<T : Any>(val data: T? = null) : State<T>()

class Effect<DATA : Any> private constructor(
    private val value: Data<DATA>,
) : State<DATA>() {

    companion object {

        fun <T : Any> success(data: T) = Effect(Success(data))

        fun <T : Any> error(
            exception: AppError,
            cachedData: T? = null,
        ) = Effect(Error(exception, cachedData))
    }

    val isSuccess: Boolean get() = value is Success<DATA>

    val isError: Boolean get() = value is Error<DATA>

    val requireData: DATA get() = requireNotNull(findData)
    val findData: DATA?
        get() = when (value) {
            is Success<DATA> -> value.data
            else -> null
        }

    val requireDataOrCache: DATA get() = requireNotNull(dataOrCache)
    val dataOrCache: DATA?
        get() = when (value) {
            is Success<DATA> -> value.data
            is Error<DATA> -> value.cache
        }

    val exceptionOrNull
        get() = when (value) {
            is Error<DATA> -> value.exception
            else -> null
        }

    suspend fun doOnSuccess(
        block: suspend (data: DATA) -> Unit,
    ): Effect<DATA> {
        if (value is Success<DATA>) {
            block(value.data)
        }
        return this
    }

    suspend fun doOnError(
        block: suspend (exception: Throwable, data: DATA?) -> Unit,
    ): Effect<DATA> {
        if (value is Error<DATA>) {
            block(value.exception, value.cache)
        }
        return this
    }

    suspend fun applyCacheData(
        cachedDataProvider: suspend () -> DATA?,
    ): Effect<DATA> = when (value) {
        is Success<DATA> -> this
        is Error<DATA> -> error(value.exception, cachedDataProvider())
    }

    suspend fun <OUT : Any> map(
        transform: suspend (value: DATA) -> OUT,
    ): Effect<OUT> = when (value) {
        is Success<DATA> -> success(transform(value.data))
        is Error<DATA> -> error(value.exception, value.cache?.let { transform(it) })
    }

    fun toCompletable() = when (value) {
        is Success<DATA> -> success(Completable)
        is Error<DATA> -> error(value.exception)
    }

    internal sealed class Data<DATA : Any>

    internal data class Success<DATA : Any>(
        val data: DATA,
    ) : Data<DATA>()

    internal data class Error<DATA : Any>(
        val exception: AppError,
        val cache: DATA? = null,
    ) : Data<DATA>()
}

inline fun <IN, OUT : Any> IN.tryRun(block: IN.() -> OUT): Effect<OUT> {
    return try {
        Effect.success(block())
    } catch (e: Exception) {
        val error = if (e is AppError) e
        else AppError.Unknown(cause = e)
        Effect.error(error)
    }
}