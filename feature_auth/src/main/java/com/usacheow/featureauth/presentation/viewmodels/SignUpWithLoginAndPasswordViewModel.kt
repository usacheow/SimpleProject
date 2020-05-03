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

class SignUpWithLoginAndPasswordViewModel
@Inject constructor(
    errorProcessor: ErrorProcessorImpl,
    private val interactor: AuthInteractor
) : NetworkRxViewModel(errorProcessor) {

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val openMainScreen: LiveData<SimpleAction> get() = _openMainScreenLiveData
    private val _openMainScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

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
                _openMainScreenLiveData.value = SimpleAction()
            }
            .build()

        disposables.clear()
        interactor.signUpWithLoginAndPassword(login, password).subscribe(observer)
    }
}