package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ApiError
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.utils.trigger
import com.usacheow.coreui.utils.tryPublish
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featureauth.R
import com.usacheow.featureauth.domain.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    private val _submitButtonEnabledState = MutableStateFlow(false)
    val submitButtonEnabledState = _submitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _closeScreenAction = EventChannel<SimpleAction>()
    val closeScreenAction = _closeScreenAction.receiveAsFlow()

    private val _openSignUpScreenAction = EventChannel<SimpleAction>()
    val openSignUpScreenAction = _openSignUpScreenAction.receiveAsFlow()

    fun onDataChanged(loginAndPassword: Pair<String, String>) {
        _submitButtonEnabledState.value = isLoginValid(loginAndPassword.first) && isPasswordValid(loginAndPassword.second)
    }

    private fun isLoginValid(login: String) = login.length >= 6

    private fun isPasswordValid(password: String) = password.length >= 6

    fun onSignInClicked(login: String, password: String) = viewModelScope.launch {
        if (!isLoginValid(login) || !isPasswordValid(password)) {
            return@launch
        }
        _isLoadingState tryPublish true

        interactor.signInWithLoginAndPassword(login, password).doOnSuccess {
            _closeScreenAction.trigger()
        }.doOnError { exception, data ->
            _errorState tryPublish when (exception) {
                is ApiError -> exception.message ?: resources.getString(exception.defaultMessageRes)
                else -> resources.getString(R.string.unknown_error_message)
            }
        }

        _isLoadingState tryPublish false
    }

    fun onSignUpClicked() = viewModelScope.launch {
        _openSignUpScreenAction.trigger()
    }
}