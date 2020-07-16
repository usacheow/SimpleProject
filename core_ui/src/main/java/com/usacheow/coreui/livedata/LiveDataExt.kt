package com.usacheow.coreui.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.subscribe(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    observe(lifecycleOwner, Observer { action(it) })
}