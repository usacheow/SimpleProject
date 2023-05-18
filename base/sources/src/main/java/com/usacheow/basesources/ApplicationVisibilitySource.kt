package com.usacheow.basesources

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

interface ApplicationVisibilitySource {

    val state: StateFlow<State>

    enum class State {
        Foreground, Background
    }
}

class ApplicationVisibilitySourceImpl(
    scope: CoroutineScope
) : ApplicationVisibilitySource {

    override val state = callbackFlow {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> this.trySend(ApplicationVisibilitySource.State.Foreground)
                Lifecycle.Event.ON_STOP -> this.trySend(ApplicationVisibilitySource.State.Background)
                else -> {}
            }
        }

        val lifecycle = ProcessLifecycleOwner.get().lifecycle
        lifecycle.addObserver(observer)

        awaitClose {
            lifecycle.removeObserver(observer)
        }
    }.stateIn(scope, SharingStarted.WhileSubscribed(), ApplicationVisibilitySource.State.Foreground)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T>.activeWhenForeground(applicationVisibilitySource: ApplicationVisibilitySource): Flow<T> {
    return applicationVisibilitySource.state.flatMapLatest {
        if (it == ApplicationVisibilitySource.State.Foreground) {
            this
        } else {
            emptyFlow()
        }
    }
}