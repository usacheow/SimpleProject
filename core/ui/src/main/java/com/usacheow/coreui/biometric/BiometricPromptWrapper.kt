package com.usacheow.coreui.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import javax.crypto.Cipher

class BiometricPromptWrapper(
    private val activity: FragmentActivity,
    private val title: String,
    private val description: String,
    private val buttonText: String,
    private val onSuccessAction: (BiometricPrompt.CryptoObject?) -> Unit = {},
    private val onUnavailableAction: () -> Unit = {},
) : BiometricPrompt.AuthenticationCallback() {

    private val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG
    private val prompt by lazy { BiometricPrompt(activity, ContextCompat.getMainExecutor(activity), this) }
    private val promptInfo by lazy {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setNegativeButtonText(buttonText)
            .setAllowedAuthenticators(authenticators)
            .build()
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        onSuccessAction(result.cryptoObject)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        if (errorCode in arrayOf(BiometricPrompt.ERROR_LOCKOUT, BiometricPrompt.ERROR_LOCKOUT_PERMANENT)) {
            onUnavailableAction()
        }
    }

    fun show(data: BiometricData) {
        prompt.authenticate(promptInfo, data.cryptoObject)
    }

    fun hide() {
        prompt.cancelAuthentication()
    }
}

class BiometricData(cipher: Cipher) {

    val cryptoObject = BiometricPrompt.CryptoObject(cipher)
}