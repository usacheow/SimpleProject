package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.doOnError
import com.usacheow.coredata.network.doOnSuccess
import com.usacheow.coreui.resource.ResourcesWrapper
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
class SignUpViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    private val _isSubmitButtonEnabledState = MutableStateFlow(false)
    val isSubmitButtonEnabledState = _isSubmitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _openMainScreenAction = Channel<SimpleAction>()
    val openMainScreenAction = _openMainScreenAction.receiveAsFlow()

    fun onDataChanged(login: String, password: String) {
        _isSubmitButtonEnabledState.value = isLoginValid(login) && isPasswordValid(password)
    }

    private fun isLoginValid(login: String) = login.length >= 6

    private fun isPasswordValid(password: String) = password.length >= 6

    fun onSignInClicked(login: String, password: String) = viewModelScope.launch {
        if (!isLoginValid(login) || !isPasswordValid(password)) {
            return@launch
        }

        _isLoadingState.emit(true)

        withContext(Dispatchers.IO) {
            interactor.signUpWithLoginAndPassword(login, password)
        }.doOnSuccess {
            _openMainScreenAction.send(SimpleAction)
        }.doOnError {
            _errorState.emit(exception.getMessage(resources.get))
        }

        _isLoadingState.emit(false)
    }
}