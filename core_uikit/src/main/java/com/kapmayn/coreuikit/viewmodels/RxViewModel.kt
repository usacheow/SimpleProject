package com.kapmayn.coreuikit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kapmayn.coreuikit.viewmodels.livedata.EventData
import io.reactivex.disposables.CompositeDisposable

open class MviViewModel<STATE, ACTION> : ViewModel() {

    protected val disposables = CompositeDisposable()

    protected val _state = MutableLiveData<STATE>()
    val state: LiveData<STATE> = _state

    protected val _action = MutableLiveData<EventData<ACTION>>()
    val action: LiveData<EventData<ACTION>> = _action

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}

abstract class SimpleRxViewModel : ViewModel() {

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}