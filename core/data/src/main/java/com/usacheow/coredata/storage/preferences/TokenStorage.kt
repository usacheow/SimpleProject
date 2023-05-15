package com.usacheow.coredata.storage.preferences

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

    fun reset() {
        decodedAccessToken = ""
        encodedRefreshToken = ""
    }
}