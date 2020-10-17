package com.usacheow.coreui.livedata

import androidx.lifecycle.MutableLiveData

var <T> MutableLiveData<T>.postValue
    get() = value
    set(value) {
        postValue(value)
    }