package com.kapmayn.core.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription

abstract class RxApplicationViewModel(application: Application) : AndroidViewModel(application) {

    protected var disposables = CompositeDisposable()
    protected var subscriptions = mutableListOf<Subscription>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        subscriptions.forEach { it.cancel() }
    }
}