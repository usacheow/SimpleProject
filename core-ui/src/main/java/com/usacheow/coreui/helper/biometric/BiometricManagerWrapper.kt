package com.usacheow.coreui.helper.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

open class BiometricManagerWrapper(
    private val activity: FragmentActivity,
    private val title: String,
    private val description: String,
    private val buttonText: String,
) : BiometricPrompt.AuthenticationCallback() {

    private val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG
    private val manager by lazy {
        BiometricManager.from(activity)
    }
    private val prompt by lazy {
        BiometricPrompt(activity, ContextCompat.getMainExecutor(activity), this)
    }
    private val promptInfo by lazy {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setNegativeButtonText(buttonText)
            .setAllowedAuthenticators(authenticators)
            .build()
    }

    protected fun show(data: BiometricData) {
        prompt.authenticate(promptInfo, data.cryptoObject)
    }

    protected fun show() {
        prompt.authenticate(promptInfo)
    }

    fun hide() {
        prompt.cancelAuthentication()
    }

    fun isBiometricAvailable(): Boolean = manager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
}

data class BiometricData(
    val cryptoObject: BiometricPrompt.CryptoObject,
)