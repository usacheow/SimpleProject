package com.usacheow.coreuikit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.error.ErrorProcessor
import com.usacheow.coredata.error.MappedException
import com.usacheow.coreuikit.viewmodels.livedata.EventData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class MviViewModel<STATE, ACTION>(
    errorProcessor: ErrorProcessor
) : SimpleRxViewModel(errorProcessor) {

    protected val _state = MutableLiveData<STATE>()
    val state: LiveData<STATE> = _state

    protected val _action = MutableLiveData<EventData<ACTION>>()
    val action: LiveData<EventData<ACTION>> = _action
}

abstract class SimpleRxViewModel(
    private val errorProcessor: ErrorProcessor
) : ViewModel() {

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    protected fun <T> Single<T>.defaultSubscribe(onSuccess: (T) -> Unit) = subscribe(
        onSuccess,
        ::onError
    )

    protected fun Completable.defaultSubscribe(onSuccess: () -> Unit) = subscribe(onSuccess, ::onError)

    private fun onError(throwable: Throwable) {
        showError(errorProcessor.process(throwable))
    }

    protected open fun showError(exception: MappedException) {}
}