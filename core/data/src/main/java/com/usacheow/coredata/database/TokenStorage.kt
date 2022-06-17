package com.usacheow.coredata.database

import androidx.core.content.edit
import com.usacheow.corecommon.model.Token
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenStorage @Inject constructor(
    private val provider: PreferencesProvider,
) {

    var decodedAccessToken: Token = ""

    var encodedRefreshToken: Token
        get() = provider.cryptoPrefs.getString("encodedRefreshToken", "").orEmpty()
        set(value) = provider.cryptoPrefs.edit {
            putString("encodedRefreshToken", value)
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
        encodedRefreshToken = ""
        salt = ""
        encodedPinCode = ""
    }
}