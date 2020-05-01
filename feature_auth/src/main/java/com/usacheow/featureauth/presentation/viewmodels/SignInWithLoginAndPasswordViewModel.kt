package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usacheow.coredata.network.error.ErrorProcessorImpl
import com.usacheow.coredata.network.observer.SimpleCompletableObserver
import com.usacheow.coreuikit.viewmodels.NetworkRxViewModel
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import com.usacheow.coreuikit.viewmodels.livedata.SimpleAction
import com.usacheow.featureauth.domain.AuthInteractor
import javax.inject.Inject

class SignInWithLoginAndPasswordViewModel
@Inject constructor(
    errorProcessor: ErrorProcessorImpl,
    private val interactor: AuthInteractor
) : NetworkRxViewModel(errorProcessor) {

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val closeScreen: LiveData<SimpleAction> get() = _closeScreenLiveData
    private val _closeScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openSignUpScreen: LiveData<SimpleAction> get() = _openSignUpScreenLiveData
    private val _openSignUpScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

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
            .onSubscribe { _isLoadingStateLiveData.postValue(true) }
            .onError(::onError)
            .onSuccess {
                _isLoadingStateLiveData.value = false
                _closeScreenLiveData.value = SimpleAction()
            }
            .build()

        disposables.clear()
        interactor.signInWithLoginAndPassword(login, password, observer)
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }

    override fun onCleared() {
        interactor.onDetach()
        super.onCleared()
    }
}