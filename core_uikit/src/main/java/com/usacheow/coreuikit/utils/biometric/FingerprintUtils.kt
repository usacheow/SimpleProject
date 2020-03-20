package com.usacheow.coreuikit.utils.biometric

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import javax.inject.Inject

class FingerprintUtils
@Inject constructor(private val context: Context) {

    fun isBiometricEnabled(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    fun isFingerprintEnabled(): Boolean {
        return isSdkVersionSupported()
            && isPermissionGranted()
            && isHardwareSupported()
            && isFingerprintAvailable()
    }

    private fun isSdkVersionSupported(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    private fun isHardwareSupported(): Boolean {
        val fingerprintManager = FingerprintManagerCompat.from(context)
        return fingerprintManager.isHardwareDetected
    }

    private fun isFingerprintAvailable(): Boolean {
        val fingerprintManager = FingerprintManagerCompat.from(context)
        return fingerprintManager.hasEnrolledFingerprints()
    }

    private fun isPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) === PackageManager.PERMISSION_GRANTED
    }
}