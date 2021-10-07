package com.usacheow.featureauth.presentation.viewmodels

import androidx.biometric.BiometricPrompt
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coredata.database.UserDataStorage
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.utils.trigger
import com.usacheow.coreui.utils.tryPublish
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    settingsStorage: SettingsStorage,
    private val userDataStorage: UserDataStorage,
) : SimpleViewModel() {

    private val _authState = MutableStateFlow<SignInResult>(SignInResult.SignInInput)
    val authState = _authState.asStateFlow()

    private val _isBiometricAllowState = MutableStateFlow(settingsStorage.isAllowBiometric)
    val isBiometricAllowState = _isBiometricAllowState.asStateFlow()

    private val _openBiometricScreenAction = EventChannel<SimpleAction>()
    val openBiometricScreenAction = _openBiometricScreenAction.receiveAsFlow()

    fun onPinCodeInputted(pinCode: String) = viewModelScope.launch {
        _authState tryPublish when (verifyPinCode(pinCode)) {
            true -> SignInResult.SignInSuccess
            false -> SignInResult.SignInError()
        }
    }

    fun onBiometricClicked() = viewModelScope.launch {
        _openBiometricScreenAction.trigger()
    }

    fun onBiometricSucceeded(cryptoObject: BiometricPrompt.CryptoObject?) {
        _authState tryPublish SignInResult.SignInSuccess
    }

    fun onBiometricUnavailableAction() {
        _isBiometricAllowState tryPublish false
    }

    // TODO: stub
    private suspend fun verifyPinCode(inputtedPinCode: String) = inputtedPinCode == userDataStorage.pinCodeFlow.first()
}

sealed class SignInResult {

    object SignInInput : SignInResult()

    object SignInSuccess : SignInResult()

    data class SignInError(val errorText: String? = null) : SignInResult()
}