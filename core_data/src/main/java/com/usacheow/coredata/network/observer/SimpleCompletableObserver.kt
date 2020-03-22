package com.usacheow.coredata.network.observer

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

class SimpleCompletableObserver
private constructor(
    private val success: () -> Unit = {},
    private val subscribe: (Disposable) -> Unit = {},
    private val error: (Throwable) -> Unit = {}
) : CompletableObserver {

    class Builder {

        private var onSuccess: () -> Unit = {}
        private var onSubscribe: (Disposable) -> Unit = {}
        private var onError: (Throwable) -> Unit = {}

        fun onSuccess(onSuccess: () -> Unit) = apply { this.onSuccess = onSuccess }

        fun onSubscribe(onSubscribe: (Disposable) -> Unit) = apply { this.onSubscribe = onSubscribe }

        fun onError(onError: (Throwable) -> Unit) = apply { this.onError = onError }

        fun build() = SimpleCompletableObserver(
            onSuccess,
            onSubscribe,
            onError
        )
    }

    override fun onComplete() {
        success()
    }

    override fun onSubscribe(d: Disposable) {
        subscribe(d)
    }

    override fun onError(e: Throwable) {
        error(e)
    }
}