package com.usacheow.coreuikit.utils.biometric

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.usacheow.coreuikit.R
import javax.inject.Inject

open class BiometricAuthorizationManager
@Inject constructor() : BiometricManagerWrapper() {

    var onSuccessAction: (List<Int>) -> Unit = {}
    var onUnavailableAction: () -> Unit = {}

    fun init(activity: FragmentActivity) {
        super.init(
            activity,
            activity.getString(R.string.fingerprint_title),
            activity.getString(R.string.fingerprint_message),
            activity.getString(R.string.fingerprint_cancel_button)
        )
    }

    fun checkFingerprintState() {
        if (!hasBiometricScanner()) {
            onUnavailableAction.invoke()
        }
    }

    fun tryShow(data: BiometricData?) {
        if (hasBiometricScanner()) {
            show(data)
        }
    }

    fun tryShow() {
        if (hasBiometricScanner()) {
            show()
        }
    }

    override fun onSuccess(result: BiometricPrompt.AuthenticationResult) {
        super.onSuccess(result)

        onSuccessAction.invoke(emptyList())
    }

    override fun onError(errorMsg: String, errorCode: Int) {
        if (errorCode in arrayOf(ERROR_LOCKOUT, ERROR_LOCKOUT_PERMANENT)) {
            onUnavailableAction.invoke()
        }
    }

}
