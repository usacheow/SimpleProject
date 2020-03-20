package com.usacheow.coreuikit.utils.biometric

import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.core.os.CancellationSignal
import java.security.KeyStore
import java.util.UUID
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.inject.Inject

@Suppress("DEPRECATION")
class FingerprintManagerImpl
@Inject constructor(
    private val context: Context
) : FingerprintManager {

    private var cancellationSignal = CancellationSignal()
    private val keyName = UUID.randomUUID().toString()

    private lateinit var cipher: Cipher
    private lateinit var keyStore: KeyStore

    @RequiresApi(Build.VERSION_CODES.M)
    override fun displayPrompt(result: (SignInResult) -> Unit) {
        generateKey()
        initCipher()

        val cryptoObject = FingerprintManagerCompat.CryptoObject(cipher)
        val fingerprintManagerCompat = FingerprintManagerCompat.from(context)

        cancellationSignal = CancellationSignal()

        fingerprintManagerCompat.authenticate(
            cryptoObject,
            0,
            cancellationSignal,
            object : FingerprintManagerCompat.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    result(SignInSuccess)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    result(SignInError(""))
                }
            },
            null
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)
            val builder = KeyGenParameterSpec.Builder(keyName, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setUserAuthenticationRequired(true)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .build()
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            keyGenerator.init(builder)
            keyGenerator.generateKey()
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initCipher() {
        cipher = Cipher.getInstance(
            KeyProperties.KEY_ALGORITHM_AES + "/"
                + KeyProperties.BLOCK_MODE_CBC + "/"
                + KeyProperties.ENCRYPTION_PADDING_PKCS7
        )
        keyStore.load(null)
        val key = keyStore.getKey(keyName, null) as SecretKey
        cipher.init(Cipher.ENCRYPT_MODE, key)
    }

    override fun cancel() {
        cancellationSignal.cancel()
    }
}