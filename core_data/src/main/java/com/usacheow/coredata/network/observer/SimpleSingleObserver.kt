package com.usacheow.coredata.network.observer

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SimpleSingleObserver<RESULT>
private constructor(
    private val success: (RESULT) -> Unit = {},
    private val subscribe: (Disposable) -> Unit = {},
    private val error: (Throwable) -> Unit = {}
) : SingleObserver<RESULT> {

    class Builder<RESULT> {

        var onSuccess: (RESULT) -> Unit = {}
        var onSubscribe: (Disposable) -> Unit = {}
        var onError: (Throwable) -> Unit = {}

        fun onSuccess(onSuccess: (RESULT) -> Unit) = apply { this.onSuccess = onSuccess }

        fun onSubscribe(onSubscribe: (Disposable) -> Unit) = apply { this.onSubscribe = onSubscribe }

        fun onError(onError: (Throwable) -> Unit) = apply { this.onError = onError }

        fun build() = SimpleSingleObserver(
            onSuccess,
            onSubscribe,
            onError
        )
    }

    override fun onSuccess(t: RESULT) {
        success(t)
    }

    override fun onSubscribe(d: Disposable) {
        subscribe(d)
    }

    override fun onError(e: Throwable) {
        error(e)
    }
}