package com.usacheow.showcaseutils.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt

class BiometricManagerWrapper(
    context: Context,
) : BiometricPrompt.AuthenticationCallback() {

    private val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG
    private val manager by lazy { BiometricManager.from(context) }

    fun isBiometricAvailable(): Boolean = manager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
}