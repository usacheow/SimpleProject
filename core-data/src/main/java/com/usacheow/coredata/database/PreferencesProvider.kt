package com.usacheow.coredata.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_DATA_STORE = "user_data"
private const val CRYPTO_PREFERENCES = "crypto_prefs"

@Singleton
class PreferencesProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    val userDataStore get() = context.dataStore

    val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    val cryptoPrefs by lazy {
        EncryptedSharedPreferences.create(
            context,
            CRYPTO_PREFERENCES,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = USER_DATA_STORE)
    }
}

fun <T> DataStore<Preferences>.get(key: Preferences.Key<T>) = data.map { it[key] }

suspend fun <T> DataStore<Preferences>.set(key: Preferences.Key<T>, value: T) = edit {
    it[key] = value
}