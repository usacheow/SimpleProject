package com.usacheow.coredata.database

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_UI_MODE = "PREF_UI_MODE"
private const val PREF_ALLOW_FINGERPRINT = "PREF_ALLOW_FINGERPRINT"

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

    var isAllowFingerprint: Boolean
        get() = preferenceManager.getBoolean(PREF_ALLOW_FINGERPRINT, true)
        set(value) = preferenceManager.edit {
            putBoolean(PREF_ALLOW_FINGERPRINT, value).apply()
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