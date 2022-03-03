package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.corecommon.resource.ResourcesWrapper
import com.usacheow.coredata.network.getMessage
import com.usacheow.corenavigation.base.requireNextScreenDirection
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.triggerBy
import com.usacheow.coreui.viewmodel.tryPublish
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
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val _submitButtonEnabledState = MutableStateFlow(false)
    val submitButtonEnabledState = _submitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _openNextScreenAction = EventChannel<FeatureNavDirection>()
    val openNextScreenAction = _openNextScreenAction.receiveAsFlow()

    private val _openSignUpScreenAction = EventChannel<FeatureNavDirection>()
    val openSignUpScreenAction = _openSignUpScreenAction.receiveAsFlow()

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }

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
            _openNextScreenAction triggerBy nextScreenDirection
        }.doOnError { exception, _ ->
            _errorState tryPublish exception.getMessage(resources)
        }

        _isLoadingState tryPublish false
    }

    fun onSignUpClicked() = viewModelScope.launch {
        _openSignUpScreenAction triggerBy nextScreenDirection
    }
}