package com.usacheow.authorization.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.Storage
import com.usacheow.coreuikit.utils.biometric.BiometricManagerImpl
import com.usacheow.coreuikit.utils.biometric.FingerprintManagerImpl
import com.usacheow.coreuikit.utils.biometric.FingerprintUtils
import com.usacheow.coreuikit.utils.biometric.SignInError
import com.usacheow.coreuikit.utils.biometric.SignInResult
import com.usacheow.coreuikit.utils.biometric.SignInSuccess
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import javax.inject.Inject

private const val MAX_SIGN_IN_ERRORS_COUNT = 2

class PinCodeViewModel
@Inject constructor(
    private val fingerprintManager: FingerprintManagerImpl,
    private val biometricDelegate: BiometricManagerImpl,
    private val fingerprintUtils: FingerprintUtils,
    private val storage: Storage
) : ViewModel() {

    private var countOfError = 0

    val isFingerPrintEnabled: LiveData<Boolean> get() = _fingerprintEnabledLiveData
    private val _fingerprintEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val isFingerPrintDialog: LiveData<Boolean> get() = _fingerPrintDialogLiveData
    private val _fingerPrintDialogLiveData by lazy { MutableLiveData<Boolean>() }

    val changeAuthState: LiveData<SignInResult> get() = _changeAuthStateLiveData
    private val _changeAuthStateLiveData by lazy { ActionLiveData<SignInResult>() }

    init {
        _fingerprintEnabledLiveData.value = fingerprintUtils.isFingerprintEnabled()
            && storage.isAllowFingerprint
    }

    fun displayFingerprintPrompt() {
        countOfError = 0
        val manager = when (fingerprintUtils.isBiometricEnabled()) {
            true -> biometricDelegate
            false -> {
                _fingerPrintDialogLiveData.value = true
                fingerprintManager
            }
        }
        manager.displayPrompt {
            _changeAuthStateLiveData.value = it
            if (it is SignInError) countOfError++
            if (countOfError >= MAX_SIGN_IN_ERRORS_COUNT) {
                manager.cancel()
                _fingerPrintDialogLiveData.value = false
                _fingerprintEnabledLiveData.value = false
            }
        }
    }

    fun onPinCodeInputted(pinCode: String) {
        _changeAuthStateLiveData.value = when (verifyPinCode(pinCode)) {
            true -> SignInSuccess
            false -> SignInError()
        }
    }

    private fun verifyPinCode(inputtedPinCode: String) = inputtedPinCode == storage.pinCode
}