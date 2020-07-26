package com.usacheow.featureauth.presentation.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.database.Storage
import com.usacheow.coreui.livedata.ActionLiveData

class PinCodeViewModel
@ViewModelInject constructor(
    private val storage: Storage,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isFingerprintAllow: LiveData<Boolean> get() = _fingerprintAllowLiveData
    private val _fingerprintAllowLiveData by lazy { MutableLiveData<Boolean>() }

    val changeAuthState: LiveData<SignInResult> get() = _changeAuthStateLiveData
    private val _changeAuthStateLiveData by lazy { ActionLiveData<SignInResult>() }

    init {
        _fingerprintAllowLiveData.value = storage.isAllowFingerprint
    }

    fun onPinCodeInputted(pinCode: String) {
        _changeAuthStateLiveData.value = when (verifyPinCode(pinCode)) {
            true -> SignInSuccess
            false -> SignInError()
        }
    }

    //TODO: stub
    private fun verifyPinCode(inputtedPinCode: String) = inputtedPinCode == storage.pinCode
}

sealed class SignInResult

object SignInSuccess : SignInResult()
data class SignInError(val errorText: String? = null) : SignInResult()