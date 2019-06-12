package com.kapmayn.core.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kapmayn.core.presentation.viewmodels.livedata.ActionLiveData
import com.kapmayn.core.presentation.viewmodels.livedata.NotNullLiveData
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription

abstract class RxApplicationViewModel<STATE, ACTION>(application: Application) : AndroidViewModel(application) {

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

abstract class SimpleRxApplicationViewModel(application: Application) : AndroidViewModel(application) {

    protected var disposables = CompositeDisposable()
    protected var subscriptions = mutableListOf<Subscription>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        subscriptions.forEach { it.cancel() }
    }
}