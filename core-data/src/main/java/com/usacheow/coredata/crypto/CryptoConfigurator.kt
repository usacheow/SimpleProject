package com.usacheow.coredata.crypto

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import androidx.biometric.BiometricPrompt
import dagger.hilt.android.qualifiers.ApplicationContext
import java.math.BigInteger
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.MGF1ParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource
import javax.inject.Inject
import javax.security.auth.x500.X500Principal

private const val KEY_FOR_BIOMETRIC = "KEY_FOR_BIOMETRIC"
private const val KEY_STORE = "AndroidKeyStore"
private const val TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"

class CryptoConfigurator @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private lateinit var cipher: Cipher
    private lateinit var keyStore: KeyStore
    private lateinit var keyPairGenerator: KeyPairGenerator

    fun deleteBiometricKey() = try {
        initKeyStore()
        keyStore.deleteEntry(KEY_FOR_BIOMETRIC)
    } catch (exception: Exception) {
        Log.d("CryptoConfigurator", "deleteBiometricKey: $exception")
    }

    fun createCryptoObject(): BiometricPrompt.CryptoObject? = ifCipherInit(KEY_FOR_BIOMETRIC, Cipher.DECRYPT_MODE) {
        BiometricPrompt.CryptoObject(cipher)
    }

    fun encode(inputString: String): String? = ifCipherInit(KEY_FOR_BIOMETRIC, Cipher.ENCRYPT_MODE) {
        inputString.toByteArray()
            .run(cipher::doFinal)
            .run { Base64.encodeToString(this, Base64.NO_WRAP) }
    }

    fun decode(encodedString: String, cipher: Cipher): String? = ifCipherInit(KEY_FOR_BIOMETRIC, Cipher.DECRYPT_MODE) {
        Base64.decode(encodedString, Base64.NO_WRAP)
            .run(cipher::doFinal)
            .run { String(this) }
    }

    private fun <T> ifCipherInit(keyTag: String, mode: Int, block: () -> T): T? {
        try {
            initKeyStore()
            createKeyIfNotExist(keyTag)
            if (initCipher(mode, keyTag)) {
                return block()
            }
        } catch (e: Exception) {
            Log.d("CryptoConfigurator", "initKeyStore: $e")
            if (wasBiometricDataChanged(e)) {
                keyStore.deleteEntry(keyTag)
            }
        }

        return null
    }

    @Throws
    private fun initKeyStore() {
        keyStore = KeyStore.getInstance(KEY_STORE)
            .apply { load(null) }
    }

    @Throws
    private fun createKeyIfNotExist(keyTag: String) {
        if(!keyStore.containsAlias(keyTag)) {
            val params = createAlgorithmParameterSpec(keyTag, isForBiometric = keyTag == KEY_FOR_BIOMETRIC)
            keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, KEY_STORE)
                .apply {
                    initialize(params)
                    generateKeyPair()
                }
        }
    }

    private fun createAlgorithmParameterSpec(keyTag: String, isForBiometric: Boolean): AlgorithmParameterSpec {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            KeyPairGeneratorSpec.Builder(context)
                .setAlias(keyTag)
                .setSubject(X500Principal("CN=$keyTag"))
                .setSerialNumber(BigInteger.TEN)
                .setStartDate(Calendar.getInstance().time)
                .setEndDate(Calendar.getInstance()
                    .apply { add(Calendar.YEAR, 1) }.time)
                .setKeyType(KeyProperties.KEY_ALGORITHM_RSA)
                .build()
        } else {
            KeyGenParameterSpec.Builder(keyTag, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .setUserAuthenticationRequired(isForBiometric)
                .build()
        }
    }

    @Throws
    private fun initCipher(mode: Int, keyTag: String): Boolean = when (mode) {
        Cipher.ENCRYPT_MODE -> initCipherForEncryptMode(mode, keyTag)
        Cipher.DECRYPT_MODE -> initCipherForDecryptMode(mode, keyTag)
        else -> false
    }

    @Throws
    private fun initCipherForEncryptMode(mode: Int, keyTag: String): Boolean {
        cipher = Cipher.getInstance(TRANSFORMATION)
        keyStore.load(null)

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

        return true
    }

    @Throws
    private fun initCipherForDecryptMode(mode: Int, keyTag: String): Boolean {
        cipher = Cipher.getInstance(TRANSFORMATION)
        keyStore.load(null)

        cipher.init(
            mode,
            keyStore.getKey(keyTag, null) as PrivateKey,
        )

        return true
    }

    private fun wasBiometricDataChanged(exception: Exception) =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && exception is KeyPermanentlyInvalidatedException
}
