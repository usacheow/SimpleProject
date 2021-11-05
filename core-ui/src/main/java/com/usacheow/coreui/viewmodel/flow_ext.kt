package com.usacheow.coreui.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.observe(lifecycleOwner: LifecycleOwner, action: suspend (value: T) -> Unit) {
    onEach(action)
        .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

suspend infix fun <T> MutableStateFlow<T>.publish(value: T) { emit(value) }

infix fun <T> MutableStateFlow<T>.tryPublish(value: T) { tryEmit(value) }

suspend infix fun <T> MutableSharedFlow<T>.publish(value: T) { emit(value) }

infix fun <T> MutableSharedFlow<T>.tryPublish(value: T) { tryEmit(value) }

suspend infix fun <T> Channel<T>.triggerBy(value: T) { send(value) }
suspend fun Channel<SimpleAction>.trigger() { send(SimpleAction) }

fun <T> Flow<T>.likeStateFlow(
    scope: CoroutineScope,
    initialValue: T
): StateFlow<T> = this.stateIn(
    scope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue,
)

fun <T> Flow<T>.likeStateFlow(
    scope: CoroutineScope,
): SharedFlow<T> = this.shareIn(
    scope,
    started = SharingStarted.WhileSubscribed(5_000),
    replay = 1,
)

fun <T> MutableStateFlowWithoutState() = MutableSharedFlow<T>(
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
    replay = 1,
)

fun <T> EventChannel() = Channel<T>()