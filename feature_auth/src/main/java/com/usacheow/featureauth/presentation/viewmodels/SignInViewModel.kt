package com.usacheow.featureauth.presentation.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ifError
import com.usacheow.coredata.network.ifSuccess
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction
import com.usacheow.coreui.livedata.postValue
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.viewmodels.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel
@ViewModelInject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : SimpleViewModel() {

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData = MutableLiveData<Boolean>(false)

    val closeScreen: LiveData<SimpleAction> get() = _closeScreenLiveData
    private val _closeScreenLiveData = ActionLiveData<SimpleAction>()

    val openSignUpScreen: LiveData<SimpleAction> get() = _openSignUpScreenLiveData
    private val _openSignUpScreenLiveData = ActionLiveData<SimpleAction>()

    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData
    private val _isLoadingStateLiveData = MutableLiveData<Boolean>(false)

    val errorState: LiveData<String?> get() = _errorStateLiveData
    private val _errorStateLiveData = MutableLiveData<String?>()

    fun onDataChanged(login: String, password: String) {
        _submitButtonEnabledLiveData.value = isLoginValid(login) && isPasswordValid(password)
    }

    private fun isLoginValid(login: String) = login.length >= 6

    private fun isPasswordValid(password: String) = password.length >= 6

    fun onSignInClicked(login: String, password: String) {
        if (!isLoginValid(login) || !isPasswordValid(password)) return

        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingStateLiveData.postValue = true
            interactor.signInWithLoginAndPassword(login, password)
                .ifSuccess {
                    _closeScreenLiveData.postValue = SimpleAction()
                }.ifError {
                    _errorStateLiveData.postValue = exception.getMessage(resources.get)
                }
            _isLoadingStateLiveData.postValue = false
        }
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }
}