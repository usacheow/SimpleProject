package com.usacheow.coreuikit.utils.biometric

import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import com.usacheow.coreuikit.R
import javax.inject.Inject

class BiometricManagerImpl
@Inject constructor(
    private val context: Context
) : FingerprintManager {

    private var cancellationSignal = CancellationSignal()

    @TargetApi(Build.VERSION_CODES.P)
    override fun displayPrompt(result: (SignInResult) -> Unit) {
        val cancelListener = DialogInterface.OnClickListener { _, _ -> }

        cancellationSignal = CancellationSignal()

        BiometricPrompt.Builder(context)
            .setTitle(context.resources.getString(R.string.fingerprint_title))
            .setDescription(context.resources.getString(R.string.fingerprint_message))
            .setNegativeButton(context.resources.getString(R.string.fingerprint_cancel_button), context.mainExecutor, cancelListener)
            .build()
            .authenticate(
                cancellationSignal,
                context.mainExecutor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        result(SignInSuccess)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        result(SignInError(""))
                    }
                }
            )
    }

    override fun cancel() {
        cancellationSignal.cancel()
    }
}