package com.usacheow.coreui.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BiometricManagerWrapper @Inject constructor(
    @ApplicationContext context: Context,
) : BiometricPrompt.AuthenticationCallback() {

    private val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG
    private val manager by lazy { BiometricManager.from(context) }

    fun isBiometricAvailable(): Boolean = manager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
}