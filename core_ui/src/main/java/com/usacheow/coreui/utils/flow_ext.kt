package com.usacheow.coreui.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.observe(lifecycle: Lifecycle, action: suspend (value: T) -> Unit) {
    onEach(action)
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .launchIn(lifecycle.coroutineScope)
}

suspend fun <T> T.sendTo(flow: MutableStateFlow<T>) {
    flow.emit(this)
}