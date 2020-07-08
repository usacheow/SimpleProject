package com.usacheow.coreuikit.utils.biometric

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.usacheow.coreuikit.R
import javax.inject.Inject

class BiometricRegistrationManager
@Inject constructor() : BiometricManagerWrapper() {

    var onSuccessAction: (BiometricPrompt.AuthenticationResult) -> Unit = {}
    var onErrorAction: () -> Unit = {}
    var onShowMessageAction: () -> Unit = {}

    fun init(activity: FragmentActivity) {
        super.init(
            activity,
            activity.getString(R.string.fingerprint_title),
            activity.getString(R.string.fingerprint_message),
            activity.getString(R.string.fingerprint_cancel_button)
        )
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
        onSuccessAction.invoke(result)
    }

    override fun onError(errorMsg: String, errorCode: Int) {
        onErrorAction.invoke()

        if (errorCode == ERROR_LOCKOUT || errorCode == ERROR_LOCKOUT_PERMANENT) {
            onShowMessageAction.invoke()
        }
    }

}
