package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.database.Storage
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val storage: Storage,
) : SimpleViewModel() {

    private val _isFingerprintAllowState = MutableStateFlow(storage.isAllowFingerprint)
    val isFingerprintAllowState = _isFingerprintAllowState.asStateFlow()

    private val _changeAuthState = MutableStateFlow<SignInResult>(SignInInput)
    val changeAuthState = _changeAuthState.asStateFlow()

    fun onPinCodeInputted(pinCode: String) = viewModelScope.launch {
        _changeAuthState.emit(
            when (verifyPinCode(pinCode)) {
                true -> SignInSuccess
                false -> SignInError()
            }
        )
    }

    //TODO: stub
    private fun verifyPinCode(inputtedPinCode: String) = inputtedPinCode == storage.pinCode
}

sealed class SignInResult

object SignInInput : SignInResult()
object SignInSuccess : SignInResult()
data class SignInError(val errorText: String? = null) : SignInResult()