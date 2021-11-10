package com.usacheow.coredata.database

import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_TOKEN = "PREF_TOKEN"

@Singleton
class TokenStorage @Inject constructor(
    private val provider: PreferencesProvider,
) {

    var token: String
        get() = provider.cryptoPrefs.getString(PREF_TOKEN, "").orEmpty()
        set(value) = provider.cryptoPrefs.edit {
            putString(PREF_TOKEN, value)
        }
}