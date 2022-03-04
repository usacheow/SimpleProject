package com.usacheow.featureauth.presentation.biometric

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import com.usacheow.coreuiview.resourcewrapper.ResourcesWrapper
import javax.inject.Inject
import com.usacheow.corecommon.R as CoreR

interface BiometricCreateManager {

    var onSuccessAction: (BiometricPrompt.CryptoObject?) -> Unit

    var onErrorAction: () -> Unit

    var onLockoutAction: () -> Unit

    fun tryShow(data: BiometricData)

    fun tryShow()

    fun hide()

    fun isBiometricAvailable(): Boolean
}

class BiometricCreateManagerImpl @Inject constructor(
    resources: ResourcesWrapper,
    fragment: Fragment,
) : BiometricManagerWrapper(
    activity = fragment.requireActivity(),
    title = resources.getString(CoreR.string.biometric_create_title),
    description = resources.getString(CoreR.string.biometric_create_message),
    buttonText = resources.getString(CoreR.string.biometric_create_cancel),
), BiometricCreateManager {

    override var onSuccessAction: (BiometricPrompt.CryptoObject?) -> Unit = {}
    override var onErrorAction: () -> Unit = {}
    override var onLockoutAction: () -> Unit = {}

    override fun tryShow(data: BiometricData) {
        if (isBiometricAvailable()) {
            show(data)
        }
    }

    override fun tryShow() {
        if (isBiometricAvailable()) {
            show()
        }
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        onSuccessAction(result.cryptoObject)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        onErrorAction()

        if (errorCode in arrayOf(BiometricPrompt.ERROR_LOCKOUT, BiometricPrompt.ERROR_LOCKOUT_PERMANENT)) {
            onLockoutAction()
        }
    }
}