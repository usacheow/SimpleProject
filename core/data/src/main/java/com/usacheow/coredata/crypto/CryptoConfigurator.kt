package com.usacheow.coredata.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.MGF1ParameterSpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource
import javax.inject.Inject

private const val KEY_FOR_BIOMETRIC = "KEY_FOR_BIOMETRIC"
private const val KEY_STORE = "AndroidKeyStore"
private const val TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"

class CryptoConfigurator @Inject constructor() {

    private var keyStore = KeyStore.getInstance(KEY_STORE).apply { load(null) }

    fun deleteBiometricKey() = try {
        keyStore.deleteEntry(KEY_FOR_BIOMETRIC)
    } catch (exception: Exception) {
        Log.d("CryptoConfigurator", "deleteBiometricKey: $exception")
    }

    @Throws
    fun createBiometricDecryptCipher() = initCipher(KEY_FOR_BIOMETRIC, Cipher.DECRYPT_MODE)

    @Throws
    fun createBiometricEncryptCipher() = initCipher(KEY_FOR_BIOMETRIC, Cipher.ENCRYPT_MODE)

    @Throws
    fun encode(inputString: String, cipher: Cipher): String {
        return inputString.toByteArray()
            .run(cipher::doFinal)
            .run { Base64.encodeToString(this, Base64.NO_WRAP) }
    }

    @Throws
    fun decode(encodedString: String, cipher: Cipher): String {
        return Base64.decode(encodedString, Base64.NO_WRAP)
            .run(cipher::doFinal)
            .run { String(this) }
    }

    @Throws
    private fun initCipher(keyTag: String, mode: Int): Cipher? {
        try {
            createKeyIfNotExist(keyTag)
            return initCipher(mode, keyTag)
        } catch (e: Exception) {
            Log.d("CryptoConfigurator", "initKeyStore: $e")
            if (e.wasBiometricDataChanged()) {
                keyStore.deleteEntry(keyTag)
                return null
            }
            throw e
        }
    }

    @Throws
    private fun createKeyIfNotExist(keyTag: String) {
        if (!keyStore.containsAlias(keyTag)) {
            val params = createAlgorithmParameterSpec(keyTag, isForBiometric = keyTag == KEY_FOR_BIOMETRIC)
            KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEY_STORE).apply {
                initialize(params)
                generateKeyPair()
            }
        }
    }

    private fun createAlgorithmParameterSpec(keyTag: String, isForBiometric: Boolean): AlgorithmParameterSpec {
        return KeyGenParameterSpec.Builder(keyTag, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
            .setUserAuthenticationRequired(isForBiometric)
            .build()
    }

    @Throws
    private fun initCipher(mode: Int, keyTag: String): Cipher = when (mode) {
        Cipher.ENCRYPT_MODE -> initCipherForEncryptMode(mode, keyTag)
        Cipher.DECRYPT_MODE -> initCipherForDecryptMode(mode, keyTag)
        else -> throw IllegalArgumentException()
    }

    @Throws
    private fun initCipherForEncryptMode(mode: Int, keyTag: String): Cipher {
        val cipher = Cipher.getInstance(TRANSFORMATION)

        // workaround for using public key
        // from https://developer.android.com/reference/android/security/keystore/KeyGenParameterSpec.html
        // and from https://code.google.com/p/android/issues/detail?id=197719
        cipher.init(
            mode,
            keyStore.getCertificate(keyTag).publicKey.let {
                KeyFactory.getInstance(it.algorithm)
                    .generatePublic(X509EncodedKeySpec(it.encoded))
            },
            OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT),
        )

        return cipher
    }

    @Throws
    private fun initCipherForDecryptMode(mode: Int, keyTag: String): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(mode, keyStore.getKey(keyTag, null) as PrivateKey)
        }
    }

    private fun Exception.wasBiometricDataChanged() = this is KeyPermanentlyInvalidatedException
}