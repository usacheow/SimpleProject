package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.database.Storage
import com.usacheow.coreui.livedata.ActionLiveData
import javax.inject.Inject

class PinCodeViewModel
@Inject constructor(
    private val storage: Storage
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