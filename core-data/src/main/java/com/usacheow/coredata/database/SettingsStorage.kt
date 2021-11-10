package com.usacheow.coredata.database

import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_ALLOW_FINGERPRINT = "PREF_ALLOW_FINGERPRINT"
private const val PREF_IS_FIRST_ENTRY = "PREF_IS_FIRST_ENTRY"
private const val PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE = "PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE"

@Singleton
class SettingsStorage @Inject constructor(
    private val provider: PreferencesProvider,
) {

    var isAllowBiometric: Boolean
        get() = provider.prefs.getBoolean(PREF_ALLOW_FINGERPRINT, true)
        set(value) = provider.prefs.edit {
            putBoolean(PREF_ALLOW_FINGERPRINT, value)
        }

    var isFirstEntry: Boolean
        get() = provider.prefs.getBoolean(PREF_IS_FIRST_ENTRY, true)
        set(value) = provider.prefs.edit {
            putBoolean(PREF_IS_FIRST_ENTRY, value)
        }

    var lastCheckedAvailableVersionCode: Int
        get() = provider.prefs.getInt(PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE, 0)
        set(value) = provider.prefs.edit {
            putInt(PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE, value)
        }
}