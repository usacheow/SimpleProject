package com.usacheow.coredata.crypto

import android.os.Build
import android.security.keystore.KeyPermanentlyInvalidatedException
import com.usacheow.corecommon.AppError
import com.usacheow.corecommon.Completable
import com.usacheow.corecommon.Effect
import com.usacheow.coredata.database.TokenStorage
import java.security.GeneralSecurityException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class PinCodeWrapper @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val cryptoConfigurator: CryptoConfigurator,
) {

    fun isValid(pinCode: String) = pinCode != "2580" &&
            pinCode.toSet().size > 1 &&
            (0 until pinCode.length - 1).any { pinCode[it] - pinCode[it + 1] != 1 } &&
            (0 until pinCode.length - 1).any { pinCode[it + 1] - pinCode[it] != 1 }

    fun save(pinCode: String, accessToken: String, refreshToken: String): Effect<Completable> {
        tokenStorage.salt = generateSalt().decodeToString()
        val secretKey = Pbkdf2Factory.createKey(pinCode.toCharArray(), tokenStorage.salt.encodeToByteArray())
        val cipher = createEncryptCipher(secretKey)

        tokenStorage.decodedAccessToken = accessToken
        tokenStorage.accessToken = cryptoConfigurator.encode(accessToken, cipher)
        tokenStorage.refreshToken = cryptoConfigurator.encode(refreshToken, cipher)

        return Effect.success(Completable)
    }

    fun check(pin: String): Effect<Completable> {
        val salt = tokenStorage.salt.encodeToByteArray()
        val secretKey = Pbkdf2Factory.createKey(pin.toCharArray(), salt)
        val cipher = createDecryptCipher(secretKey)

        val token = try {
            val encryptedAccessToken = tokenStorage.accessToken
            cryptoConfigurator.decode(encryptedAccessToken, cipher)
        } catch (e: GeneralSecurityException) {
            null
        }

        return if (token == null) {
            Effect.error(AppError.Unknown(cause = GeneralSecurityException()))
        } else {
            Effect.success(Completable)
        }
    }

    fun enableBiometric(pinCode: String): Effect<Completable> {
        val cipher = cryptoConfigurator.createBiometricEncryptCipher() ?: return Effect.error(AppError.Unknown())
        tokenStorage.encodedPinCode = cryptoConfigurator.encode(pinCode, cipher)
        return Effect.success(Completable)
    }

    fun getBiometricCipher(): Effect<Cipher> {
        val cipher = cryptoConfigurator.createBiometricDecryptCipher()
            ?: return Effect.error(AppError.Unknown(cause = KeyPermanentlyInvalidatedException()))
        return Effect.success(cipher)
    }

    fun isBiometricValid(cipher: Cipher?): Effect<Completable> {
        cipher ?: return Effect.error(AppError.Unknown())
        return check(CryptoConfigurator().decode(tokenStorage.encodedPinCode, cipher))
    }

    private fun createEncryptCipher(secretKey: SecretKey) = Cipher.getInstance("AES/ECB/PKCS5Padding").apply {
        init(Cipher.ENCRYPT_MODE, secretKey)
    }

    private fun createDecryptCipher(secretKey: SecretKey) = Cipher.getInstance("AES/ECB/PKCS5Padding").apply {
        init(Cipher.DECRYPT_MODE, secretKey)
    }

    private fun generateSalt(lengthByte: Int = 32): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(lengthByte)
        random.nextBytes(salt)
        return salt
    }

    private object Pbkdf2Factory {

        private const val DEFAULT_ITERATIONS = 10000
        private const val DEFAULT_KEY_LENGTH = 256

        private val secretKeyFactory by lazy {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                SecretKeyFactory.getInstance("PBKDF2withHmacSHA1")
            } else {
                SecretKeyFactory.getInstance("PBKDF2withHmacSHA256")
            }
        }

        fun createKey(
            passphraseOrPin: CharArray,
            salt: ByteArray,
            iterations: Int = DEFAULT_ITERATIONS,
            outputKeyLength: Int = DEFAULT_KEY_LENGTH
        ): SecretKey {
            val keySpec = PBEKeySpec(passphraseOrPin, salt, iterations, outputKeyLength)
            return secretKeyFactory.generateSecret(keySpec)
        }
    }
}