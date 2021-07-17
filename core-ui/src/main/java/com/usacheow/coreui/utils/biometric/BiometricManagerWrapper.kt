package com.usacheow.coreui.utils.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

open class BiometricManagerWrapper {

    private lateinit var manager: BiometricManager
    private lateinit var prompt: BiometricPrompt
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var buttonText: String
    private val promptInfo by lazy {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setNegativeButtonText(buttonText)
            .build()
    }

    companion object {
        // from BiometricPrompt.BiometricError
        const val ERROR_LOCKOUT = 7
        const val ERROR_LOCKOUT_PERMANENT = 9
    }

    fun init(activity: FragmentActivity, title: String, description: String, buttonText: String) {
        this.title = title
        this.description = description
        this.buttonText = buttonText
        manager = BiometricManager.from(activity)
        prompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity), BiometricManagerCallback())
    }

    protected fun show(data: BiometricData?) {
        data?.let {
            prompt.authenticate(promptInfo, it.cryptoObject)
        }
    }

    protected fun show() {
        prompt.authenticate(promptInfo)
    }

    fun hide() {
        prompt.cancelAuthentication()
    }

    fun hasBiometricScanner(): Boolean = manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS

    protected open fun onSuccess(result: BiometricPrompt.AuthenticationResult) = Unit

    protected open fun onError(errorMsg: String, errorCode: Int) = Unit

    protected open fun onFailed() = Unit

    private inner class BiometricManagerCallback : BiometricPrompt.AuthenticationCallback() {

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            onSuccess(result)
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            onError(errString.toString(), errorCode)
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            onFailed()
        }
    }
}

data class BiometricData(
    val cryptoObject: BiometricPrompt.CryptoObject
)