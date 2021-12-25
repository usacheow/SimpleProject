package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.core.TextSource
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coredata.network.getMessage
import com.usacheow.coreui.utils.navigation.requireNextScreenDirection
import com.usacheow.coreui.values_ext.isPhoneNumberValid
import com.usacheow.coreui.values_ext.normalizedPhoneNumber
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleAction
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.trigger
import com.usacheow.coreui.viewmodel.triggerBy
import com.usacheow.coreui.viewmodel.tryPublish
import com.usacheow.featureauth.domain.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CONFIRM_CODE_LENGTH = 4

@HiltViewModel
class SignInWithPhoneViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val _codeConfirmMessageState = MutableStateFlow<TextSource?>(null)
    val codeConfirmMessageState = _codeConfirmMessageState.asStateFlow()

    private val _isSubmitButtonEnabledState = MutableStateFlow(false)
    val isSubmitButtonEnabledState = _isSubmitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _closeSmsCodeScreenAction = EventChannel<SimpleAction>()
    val closeSmsCodeScreenAction = _closeSmsCodeScreenAction.receiveAsFlow()

    private val _openNextScreenAction = EventChannel<FeatureNavDirection>()
    val openNextScreenAction = _openNextScreenAction.receiveAsFlow()

    private val _openSignUpScreenAction = EventChannel<FeatureNavDirection>()
    val openSignUpScreenAction = _openSignUpScreenAction.receiveAsFlow()

    private val _openConfirmScreenAction = EventChannel<Int>()
    val openConfirmScreenAction = _openConfirmScreenAction.receiveAsFlow()

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }

    private var phoneNumber = ""

    fun onPhoneChanged(phone: String) {
        _isSubmitButtonEnabledState.value = phone.isPhoneNumberValid()
    }

    fun onSubmitClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    fun onSignInClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    private fun sendPhoneNumberIfValid(phone: String) = viewModelScope.launch {
        phoneNumber = phone.normalizedPhoneNumber()
        if (!phoneNumber.isPhoneNumberValid()) {
            return@launch
        }

        _isLoadingState tryPublish true

        interactor.signInWithPhone(phone).doOnSuccess {
            _openConfirmScreenAction triggerBy CONFIRM_CODE_LENGTH
        }.doOnError { exception, _ ->
            _errorState tryPublish exception.getMessage(resources)
        }

        _isLoadingState tryPublish false
    }

    fun onSignUpClicked() = viewModelScope.launch {
        _openSignUpScreenAction triggerBy nextScreenDirection
    }

    fun onCodeInputted(code: String) = viewModelScope.launch {
        if (code.isEmpty()) {
            return@launch
        }

        interactor.verifyPhone(phoneNumber, code).doOnSuccess {
            _closeSmsCodeScreenAction.trigger()
            _openNextScreenAction triggerBy nextScreenDirection
        }.doOnError { _, _ ->
            _codeConfirmMessageState tryPublish TextSource.Simple("Неверный код")
        }
    }

    fun onResendClicked() = viewModelScope.launch {
        interactor.resendCode(phoneNumber)
    }
}