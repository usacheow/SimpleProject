package com.usacheow.coredata.storage.preferences

import androidx.core.content.edit
import com.usacheow.corecommon.model.Token

class TokenStorage(
    private val provider: PreferencesProvider,
) {

    var accessToken: Token?
        get() = provider.cryptoPrefs.getString("encodedAccessToken", "").orEmpty().ifEmpty { null }
        set(value) = provider.cryptoPrefs.edit {
            putString("encodedAccessToken", value)
        }

    var refreshToken: Token
        get() = provider.cryptoPrefs.getString("encodedRefreshToken", "").orEmpty()
        set(value) = provider.cryptoPrefs.edit {
            putString("encodedRefreshToken", value)
        }

    var isActual: Boolean
        get() = provider.cryptoPrefs.getBoolean("isAccessTokenActual", false)
        set(value) = provider.cryptoPrefs.edit {
            putBoolean("isAccessTokenActual", value)
        }

    fun markAsNotActual() {
        isActual = false
    }

    fun logout() {
        accessToken = ""
        refreshToken = ""
    }
}