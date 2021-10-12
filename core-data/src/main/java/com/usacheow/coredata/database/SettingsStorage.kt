package com.usacheow.coredata.database

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_UI_MODE = "PREF_UI_MODE"
private const val PREF_ALLOW_FINGERPRINT = "PREF_ALLOW_FINGERPRINT"
private const val PREF_IS_FIRST_ENTRY = "PREF_IS_FIRST_ENTRY"
private const val PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE = "PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE"

@Singleton
class SettingsStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val preferenceManager by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    var uiMode: UiMode
        get() = preferenceManager.getString(PREF_UI_MODE, "").run { UiMode.get(this) }
        set(value) = preferenceManager.edit {
            putString(PREF_UI_MODE, value.name).apply()
        }

    var isAllowBiometric: Boolean
        get() = preferenceManager.getBoolean(PREF_ALLOW_FINGERPRINT, true)
        set(value) = preferenceManager.edit {
            putBoolean(PREF_ALLOW_FINGERPRINT, value).apply()
        }

    var isFirstEntry: Boolean
        get() = preferenceManager.getBoolean(PREF_IS_FIRST_ENTRY, true)
        set(value) = preferenceManager.edit {
            putBoolean(PREF_IS_FIRST_ENTRY, value).apply()
        }

    var lastCheckedAvailableVersionCode: Int
        get() = preferenceManager.getInt(PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE, 0)
        set(value) = preferenceManager.edit {
            putInt(PREF_LAST_CHECKED_AVAILABLE_VERSION_CODE, value).apply()
        }
}

enum class UiMode {
    LIGHT,
    NIGHT,
    SYSTEM;

    companion object {
        fun get(name: String?) = values().firstOrNull { name == it.name } ?: SYSTEM
    }
}