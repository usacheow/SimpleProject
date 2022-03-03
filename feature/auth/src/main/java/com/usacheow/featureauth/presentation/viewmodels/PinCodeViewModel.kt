package com.usacheow.featureauth.presentation.viewmodels

import androidx.biometric.BiometricPrompt
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coredata.database.UserDataStorage
import com.usacheow.corenavigation.base.requireNextScreenDirection
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleAction
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.trigger
import com.usacheow.coreui.viewmodel.triggerBy
import com.usacheow.coreui.viewmodel.tryPublish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    settingsStorage: SettingsStorage,
    private val userDataStorage: UserDataStorage,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val _authState = MutableStateFlow<SignInResult>(SignInResult.SignInInput)
    val authState = _authState.asStateFlow()

    private val _isBiometricAllowState = MutableStateFlow(settingsStorage.isAllowBiometric)
    val isBiometricAllowState = _isBiometricAllowState.asStateFlow()

    private val _openBiometricScreenAction = EventChannel<SimpleAction>()
    val openBiometricScreenAction = _openBiometricScreenAction.receiveAsFlow()

    private val _openNextScreenAction = EventChannel<FeatureNavDirection>()
    val openNextScreenAction = _openNextScreenAction.receiveAsFlow()

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }

    fun onPinCodeInputted(pinCode: String) = viewModelScope.launch {
        when (verifyPinCode(pinCode)) {
            true -> {
                _authState tryPublish SignInResult.SignInSuccess
                _openNextScreenAction triggerBy nextScreenDirection
            }
            false -> {
                _authState tryPublish SignInResult.SignInError()
            }
        }
    }

    fun onBiometricClicked() = viewModelScope.launch {
        _openBiometricScreenAction.trigger()
    }

    fun onBiometricSucceeded(cryptoObject: BiometricPrompt.CryptoObject?) = viewModelScope.launch {
        _authState tryPublish SignInResult.SignInSuccess
        _openNextScreenAction triggerBy nextScreenDirection
    }

    fun onBiometricUnavailableAction() {
        _isBiometricAllowState tryPublish false
    }

    // TODO: stub
    private fun verifyPinCode(inputtedPinCode: String) = true
}

sealed class SignInResult {

    object SignInInput : SignInResult()

    object SignInSuccess : SignInResult()

    data class SignInError(val errorText: String? = null) : SignInResult()
}