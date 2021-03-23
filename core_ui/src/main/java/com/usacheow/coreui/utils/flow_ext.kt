package com.usacheow.coreui.utils

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

inline fun <T> Flow<T>.observe(scope: LifecycleCoroutineScope, crossinline action: suspend (value: T) -> Unit) {
    scope.launchWhenStarted {
        collect(action)
    }
}