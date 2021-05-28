package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coredata.database.UserDataStorage
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    settingsStorage: SettingsStorage,
    private val userDataStorage: UserDataStorage,
) : SimpleViewModel() {

    private val _isFingerprintAllowState = MutableStateFlow(settingsStorage.isAllowFingerprint)
    val isFingerprintAllowState = _isFingerprintAllowState.asStateFlow()

    private val _changeAuthState = MutableStateFlow<SignInResult>(SignInResult.SignInInput)
    val changeAuthState = _changeAuthState.asStateFlow()

    fun onPinCodeInputted(pinCode: String) = viewModelScope.launch {
        _changeAuthState.emit(
            when (verifyPinCode(pinCode)) {
                true -> SignInResult.SignInSuccess
                false -> SignInResult.SignInError()
            }
        )
    }

    //TODO: stub
    private suspend fun verifyPinCode(inputtedPinCode: String) = inputtedPinCode == userDataStorage.pinCodeFlow.first()
}

sealed class SignInResult {

    object SignInInput : SignInResult()

    object SignInSuccess : SignInResult()

    data class SignInError(val errorText: String? = null) : SignInResult()
}