package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ApiError
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.navigation.FeatureNavDirection
import com.usacheow.coreui.utils.navigation.requireNextScreenDirection
import com.usacheow.coreui.utils.triggerBy
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
class SignUpViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val _isSubmitButtonEnabledState = MutableStateFlow(false)
    val isSubmitButtonEnabledState = _isSubmitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _openNextScreenAction = EventChannel<FeatureNavDirection>()
    val openNextScreenAction = _openNextScreenAction.receiveAsFlow()

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }

    fun onDataChanged(login: String, password: String) {
        _isSubmitButtonEnabledState.value = isLoginValid(login) && isPasswordValid(password)
    }

    private fun isLoginValid(login: String) = login.length >= 6

    private fun isPasswordValid(password: String) = password.length >= 6

    fun onSignInClicked(login: String, password: String) = viewModelScope.launch {
        if (!isLoginValid(login) || !isPasswordValid(password)) {
            return@launch
        }

        _isLoadingState tryPublish true

        interactor.signUpWithLoginAndPassword(login, password).doOnSuccess {
            _openNextScreenAction triggerBy nextScreenDirection
        }.doOnError { exception, data ->
            _errorState tryPublish when (exception) {
                is ApiError -> exception.message ?: resources.getString(exception.defaultMessageRes)
                else -> resources.getString(R.string.unknown_error_message)
            }
        }

        _isLoadingState tryPublish false
    }
}