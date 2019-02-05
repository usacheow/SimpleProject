package com.kapmayn.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription

abstract class RxViewModel<STATE, ACTION> : ViewModel() {

    val state = NotNullLiveData<STATE>()
    val action = ActionLiveData<ACTION>()

    protected var disposables = CompositeDisposable()
    protected var subscriptions = mutableListOf<Subscription>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        subscriptions.forEach { it.cancel() }
    }
}

abstract class SimpleRxViewModel : ViewModel() {

    protected var disposables = CompositeDisposable()
    protected var subscriptions = mutableListOf<Subscription>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        subscriptions.forEach { it.cancel() }
    }
}