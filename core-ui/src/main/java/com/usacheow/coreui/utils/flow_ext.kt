package com.usacheow.coreui.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.observe(lifecycleOwner: LifecycleOwner, action: suspend (value: T) -> Unit) {
    onEach(action)
        .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

suspend fun <T> T.sendTo(flow: MutableStateFlow<T>) {
    flow.emit(this)
}