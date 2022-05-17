package com.usacheow.coreui.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import javax.crypto.Cipher

class BiometricPromptWrapper(
    activity: FragmentActivity,
    private val onSuccessAction: (BiometricPrompt.CryptoObject?) -> Unit = {},
    private val onUnavailableAction: () -> Unit = {},
) : BiometricPrompt.AuthenticationCallback() {

    private val prompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity), this)

    private val promptInfoBuilder = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        onSuccessAction(result.cryptoObject)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        if (errorCode in arrayOf(BiometricPrompt.ERROR_LOCKOUT, BiometricPrompt.ERROR_LOCKOUT_PERMANENT)) {
            onUnavailableAction()
        }
    }

    fun show(
        data: BiometricData,
        title: String,
        buttonText: String,
    ) {
        val promptInfo = promptInfoBuilder.setTitle(title).setNegativeButtonText(buttonText).build()
        prompt.authenticate(promptInfo, data.cryptoObject)
    }
}

class BiometricData(cipher: Cipher) {

    val cryptoObject = BiometricPrompt.CryptoObject(cipher)
}