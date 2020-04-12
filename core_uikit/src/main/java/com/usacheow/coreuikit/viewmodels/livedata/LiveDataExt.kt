package com.usacheow.coreuikit.viewmodels.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.subscribe(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    observe(lifecycleOwner, Observer { action(it) })
}

fun <T> LiveData<EventData<T>>.subscribeOnEvent(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    observe(lifecycleOwner, Observer { it.getDataIfNotHandled()?.let(action) })
}