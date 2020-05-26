package com.usacheow.coreuikit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.network.error.ErrorProcessor
import com.usacheow.coredata.network.error.MappedException
import io.reactivex.disposables.CompositeDisposable

abstract class NetworkRxViewModel(
    private val errorProcessor: ErrorProcessor
) : ViewModel() {

    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData
    protected val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }

    val errorState: LiveData<MappedException> get() = _errorStateLiveData
    protected val _errorStateLiveData by lazy { MutableLiveData<MappedException>() }

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    protected fun onError(throwable: Throwable) {
        showError(errorProcessor.process(throwable))
    }

    protected open fun showError(exception: MappedException) {}
}

abstract class SimpleRxViewModel : ViewModel() {

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}