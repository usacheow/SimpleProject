package com.usacheow.coredata.storage.preferences

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val PREF_THEME_MODE = stringPreferencesKey("PREF_THEME_MODE")

class UserDataStorage(
    private val provider: PreferencesProvider,
) {

    val themeModeFlow: Flow<ThemeMode> get() = provider.userDataStore.get(PREF_THEME_MODE).map {
        it?.let(ThemeMode::valueOf) ?: ThemeMode.System
    }
    suspend fun setThemeMode(value: ThemeMode) {
        provider.userDataStore.set(PREF_THEME_MODE, value.name)
    }
}

enum class ThemeMode { Light, Dark, System }