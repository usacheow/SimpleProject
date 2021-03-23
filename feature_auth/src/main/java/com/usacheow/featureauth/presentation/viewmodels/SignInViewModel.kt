package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ifError
import com.usacheow.coredata.network.ifSuccess
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _closeScreenAction = Channel<SimpleAction>()
    val closeScreenAction = _closeScreenAction.receiveAsFlow()

    private val _openSignUpScreenAction = Channel<SimpleAction>()
    val openSignUpScreenAction = _openSignUpScreenAction.receiveAsFlow()

    fun onDataChanged(login: String, password: String) {
        _submitButtonEnabledState.value = isLoginValid(login) && isPasswordValid(password)
    }

    private fun isLoginValid(login: String) = login.length >= 6

    private fun isPasswordValid(password: String) = password.length >= 6

    fun onSignInClicked(login: String, password: String) = viewModelScope.launch {
        if (!isLoginValid(login) || !isPasswordValid(password)) {
            return@launch
        }
        _isLoadingState.emit(true)

        withContext(Dispatchers.IO) {
            interactor.signInWithLoginAndPassword(login, password)
        }.ifSuccess {
            _closeScreenAction.send(SimpleAction)
        }.ifError {
            _errorState.emit(exception.getMessage(resources.get))
        }

        _isLoadingState.emit(false)
    }

    fun onSignUpClicked() = viewModelScope.launch {
        _openSignUpScreenAction.send(SimpleAction)
    }
}