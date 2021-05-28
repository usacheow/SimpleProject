package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.doOnError
import com.usacheow.coredata.network.doOnSuccess
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.utils.values.normalizedPhoneNumber
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EXPECTED_PHONE_NUMBER_LENGTH = 10
private const val CONFIRM_CODE_LENGTH = 4

@HiltViewModel
class SignInWithPhoneViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    private val _codeConfirmMessageState = MutableStateFlow("")
    val codeConfirmMessageState = _codeConfirmMessageState.asStateFlow()

    private val _isSubmitButtonEnabledState = MutableStateFlow(false)
    val isSubmitButtonEnabledState = _isSubmitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _closeScreenAction = Channel<SimpleAction>()
    val closeScreenAction = _closeScreenAction.receiveAsFlow()

    private val _openSignUpScreenAction = Channel<SimpleAction>()
    val openSignUpScreenAction = _openSignUpScreenAction.receiveAsFlow()

    private val _openConfirmScreenAction = Channel<Int>()
    val openConfirmScreenAction = _openConfirmScreenAction.receiveAsFlow()

    private var phoneNumber = ""

    fun onPhoneChanged(phone: String) {
        _isSubmitButtonEnabledState.value = isValidPhoneNumber(phone.normalizedPhoneNumber())
    }

    private fun isValidPhoneNumber(phone: String) = phone.length == EXPECTED_PHONE_NUMBER_LENGTH

    fun onSubmitClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    fun onSignInClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    private fun sendPhoneNumberIfValid(phone: String) = viewModelScope.launch {
        phoneNumber = phone.normalizedPhoneNumber()
        if (!isValidPhoneNumber(phoneNumber)) {
            return@launch
        }

        _isLoadingState.emit(true)

        interactor.signInWithPhone(phone).doOnSuccess {
            _openConfirmScreenAction.send(CONFIRM_CODE_LENGTH)
        }.doOnError {
            _errorState.emit(exception.getMessage(resources.get))
        }

        _isLoadingState.emit(false)
    }

    fun onSignUpClicked() = viewModelScope.launch {
        _openSignUpScreenAction.send(SimpleAction)
    }

    fun onCodeInputted(code: String) = viewModelScope.launch {
        if (code.isEmpty()) {
            return@launch
        }

        _isLoadingState.emit(true)

        interactor.verifyPhone(phoneNumber, code).doOnSuccess {
            _closeScreenAction.send(SimpleAction)
        }.doOnError {
            _codeConfirmMessageState.emit("Неверный код")
        }

        _isLoadingState.emit(false)
    }

    fun onResendClicked() = viewModelScope.launch {
        interactor.resendCode(phoneNumber)
    }
}