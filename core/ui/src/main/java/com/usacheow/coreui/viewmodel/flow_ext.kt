package com.usacheow.coreui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@Composable
inline fun <reified T> Flow<T>.collectAsCommand(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEach(action)
            .flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
            .launchIn(this)
    }
}

fun <T> MutableStateFlow<T>.publish(function: (T) -> T) { update(function) }
infix fun <T> MutableStateFlow<T>.tryPublish(value: T) { tryEmit(value) }

suspend infix fun <T> Channel<T>.publish(value: T) { send(value) }

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