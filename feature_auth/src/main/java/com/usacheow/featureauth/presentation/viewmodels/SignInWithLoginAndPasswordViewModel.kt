package com.usacheow.featureauth.presentation.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.usacheow.coredata.network.error.ErrorProcessorImpl
import com.usacheow.coredata.network.error.MappedException
import com.usacheow.coredata.network.observer.SimpleCompletableObserver
import com.usacheow.coredata.network.setRequestThreads
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction
import com.usacheow.coreui.viewmodels.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import io.reactivex.rxkotlin.plusAssign

class SignInWithLoginAndPasswordViewModel
@ViewModelInject constructor(
    private val errorProcessor: ErrorProcessorImpl,
    private val interactor: AuthInteractor,
    @Assisted private val savedStateHandle: SavedStateHandle
) : SimpleViewModel() {

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val closeScreen: LiveData<SimpleAction> get() = _closeScreenLiveData
    private val _closeScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openSignUpScreen: LiveData<SimpleAction> get() = _openSignUpScreenLiveData
    private val _openSignUpScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData
    protected val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }

    val errorState: LiveData<MappedException> get() = _errorStateLiveData
    protected val _errorStateLiveData by lazy { MutableLiveData<MappedException>() }

    init {
        _isLoadingStateLiveData.value = false
        _submitButtonEnabledLiveData.value = false
    }

    fun onDataChanged(login: String, password: String) {
        _submitButtonEnabledLiveData.value = isLoginValid(login) && isPasswordValid(password)
    }

    private fun isLoginValid(login: String) = login.length >= 6

    private fun isPasswordValid(password: String) = password.length >= 6

    fun onSignInClicked(login: String, password: String) {
        if (!isLoginValid(login) || !isPasswordValid(password)) return

        val observer = SimpleCompletableObserver.Builder()
            .onSubscribe {
                _isLoadingStateLiveData.postValue(true)
                disposables += it
            }
            .onSuccess {
                _isLoadingStateLiveData.value = false
                _closeScreenLiveData.value = SimpleAction()
            }
            .onError {
                _errorStateLiveData.value = errorProcessor.process(it)
            }
            .build()

        disposables.clear()
        interactor.signInWithLoginAndPassword(login, password)
            .setRequestThreads()
            .subscribe(observer)
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }
}