package com.usacheow.coredata.database

import androidx.core.content.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val PREF_NAME = stringPreferencesKey("PREF_NAME")
private val PREF_PHONE = stringPreferencesKey("PREF_PHONE")
private val PREF_THEME_MODE = stringPreferencesKey("PREF_THEME_MODE")

@Singleton
class UserDataStorage @Inject constructor(
    private val provider: PreferencesProvider,
) {

    val userNameFlow: Flow<String?> get() = provider.userDataStore.get(PREF_NAME)
    suspend fun setUserName(value: String) {
        provider.userDataStore.set(PREF_NAME, value)
    }

    val phoneNumberFlow: Flow<String?> get() = provider.userDataStore.get(PREF_PHONE)
    suspend fun setPhoneNumber(value: String) {
        provider.userDataStore.set(PREF_PHONE, value)
    }

    val themeModeFlow: Flow<ThemeMode> get() = provider.userDataStore.get(PREF_THEME_MODE).map {
        it?.let(ThemeMode::valueOf) ?: ThemeMode.System
    }
    suspend fun setThemeMode(value: ThemeMode) {
        provider.userDataStore.set(PREF_THEME_MODE, value.name)
    }

    var isAllowBiometric: Boolean
        get() = provider.prefs.getBoolean("isAllowBiometric", true)
        set(value) = provider.prefs.edit {
            putBoolean("isAllowBiometric", value)
        }

    var isFirstEntry: Boolean
        get() = provider.prefs.getBoolean("isFirstEntry", true)
        set(value) = provider.prefs.edit {
            putBoolean("isFirstEntry", value)
        }

    var lastCheckedAvailableVersionCode: Int
        get() = provider.prefs.getInt("lastCheckedAvailableVersionCode", 0)
        set(value) = provider.prefs.edit {
            putInt("lastCheckedAvailableVersionCode", value)
        }
}

enum class ThemeMode { Light, Dark, System }