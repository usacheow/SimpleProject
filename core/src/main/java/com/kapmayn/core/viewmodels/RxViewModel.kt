package com.kapmayn.core.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription
import javax.inject.Inject

abstract class RxViewModel : ViewModel() {

    @Inject
    lateinit var disposables: CompositeDisposable
    val subscriptions = mutableListOf<Subscription>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        subscriptions.forEach { it.cancel() }
    }
}