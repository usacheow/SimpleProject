package com.usacheow.coredata.database

import androidx.core.content.edit
import com.usacheow.corecommon.Token
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenStorage @Inject constructor(
    private val provider: PreferencesProvider,
) {

    var decodedAccessToken: Token = ""

    var accessToken: Token
        get() = provider.cryptoPrefs.getString("accessToken", "").orEmpty()
        set(value) = provider.cryptoPrefs.edit {
            putString("accessToken", value)
        }

    var refreshToken: Token
        get() = provider.cryptoPrefs.getString("refreshToken", "").orEmpty()
        set(value) = provider.cryptoPrefs.edit {
            putString("refreshToken", value)
        }

    val pinCodeLength: Int = 4

    var salt: String
        get() = provider.cryptoPrefs.getString("salt", "").orEmpty()
        set(value) = provider.cryptoPrefs.edit {
            putString("salt", value)
        }

    var encodedPinCode: String
        get() = provider.prefs.getString("encodedPinCode", "").orEmpty()
        set(value) = provider.prefs.edit {
            putString("encodedPinCode", value)
        }

    fun reset() {
        decodedAccessToken = ""
        accessToken = ""
        refreshToken = ""
        salt = ""
        encodedPinCode = ""
    }
}