package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ifError
import com.usacheow.coredata.network.ifSuccess
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction
import com.usacheow.coreui.livedata.postValue
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.viewmodels.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpWithLoginAndPasswordViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val openMainScreen: LiveData<SimpleAction> get() = _openMainScreenLiveData
    private val _openMainScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData
    private val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }

    val errorState: LiveData<String?> get() = _errorStateLiveData
    private val _errorStateLiveData by lazy { MutableLiveData<String?>() }

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

        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingStateLiveData.postValue = true
            interactor.signUpWithLoginAndPassword(login, password).ifSuccess {
                _openMainScreenLiveData.postValue = SimpleAction()
            }.ifError {
                _errorStateLiveData.postValue = exception.getMessage(resources.get)
            }
            _isLoadingStateLiveData.postValue = false
        }
    }
}