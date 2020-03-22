package com.usacheow.coredata.network

import io.reactivex.disposables.CompositeDisposable

abstract class BaseInteractor {

    protected var disposables = CompositeDisposable()

    fun onDetach() {
        disposables.clear()
    }
}