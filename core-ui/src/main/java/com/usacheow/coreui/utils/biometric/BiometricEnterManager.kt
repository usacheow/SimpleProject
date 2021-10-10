package com.usacheow.coreui.utils.biometric

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.core.resource.ResourcesWrapper
import javax.inject.Inject

interface BiometricEnterManager {

    var onSuccessAction: (BiometricPrompt.CryptoObject?) -> Unit

    var onUnavailableAction: () -> Unit

    fun tryShow(data: BiometricData)

    fun tryShow()

    fun hide()

    fun isBiometricAvailable(): Boolean
}

class BiometricEnterManagerImpl @Inject constructor(
    resources: ResourcesWrapper,
    fragment: Fragment,
) : BiometricManagerWrapper(
    activity = fragment.requireActivity(),
    title = resources.getString(CoreUiR.string.biometric_enter_title),
    description = resources.getString(CoreUiR.string.biometric_enter_message),
    buttonText = resources.getString(CoreUiR.string.biometric_enter_cancel),
), BiometricEnterManager {

    override var onSuccessAction: (BiometricPrompt.CryptoObject?) -> Unit = {}
    override var onUnavailableAction: () -> Unit = {}

    override fun tryShow(data: BiometricData) {
        if (isBiometricAvailable()) {
            show(data)
        } else {
            onUnavailableAction()
        }
    }

    override fun tryShow() {
        if (isBiometricAvailable()) {
            show()
        } else {
            onUnavailableAction()
        }
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        onSuccessAction(result.cryptoObject)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        if (errorCode in arrayOf(BiometricPrompt.ERROR_LOCKOUT, BiometricPrompt.ERROR_LOCKOUT_PERMANENT)) {
            onUnavailableAction()
        }
    }
}