package com.usacheow.coredata

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal object NotInit

@OptIn(ExperimentalCoroutinesApi::class)
class ValueHolder<T>(
    private val load: suspend () -> T
) {

    private val latestCacheFlow = MutableStateFlow(AsyncLazyValueLoader(load))

    val flow = latestCacheFlow.flatMapLatest { it.observe() }

    fun invalidate() {
        latestCacheFlow.tryEmit(AsyncLazyValueLoader(load))
    }
}

class AsyncLazyValueLoader<out T>(load: suspend () -> T) {

    private val mutex = Mutex()

    @Volatile
    private var value: Any? = NotInit

    @Volatile
    private var load: (suspend () -> T)? = load

    @Suppress("UNCHECKED_CAST")
    suspend fun get(): T = mutex.withLock {
        if (value === NotInit) {
            value = load!!.invoke()
            load = null
        }
        value as T
    }

    @OptIn(FlowPreview::class)
    fun observe(): Flow<T> = ::get.asFlow()

    fun getOrNull(): T? = if (value === NotInit) {
        null
    } else {
        @Suppress("UNCHECKED_CAST")
        value as T
    }
}