package com.usacheow.coreuikit.utils.biometric

interface FingerprintManager {

    fun displayPrompt(result: (SignInResult) -> Unit)

    fun cancel()
}