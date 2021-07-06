package com.usacheow.coredata.database

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DATA_STORE_NAME = "user_data"

private val PREF_NAME = stringPreferencesKey("PREF_NAME")
private val PREF_PHONE = stringPreferencesKey("PREF_PHONE")
private val PREF_PIN_CODE = stringPreferencesKey("PREF_PIN_CODE")
private val PREF_IS_PAYED = booleanPreferencesKey("PREF_IS_PAYED")

@Singleton
class UserDataStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

    val userNameFlow: Flow<String?> get() = get(PREF_NAME)
    suspend fun setUserName(value: String) {
        set(PREF_NAME, value)
    }

    val phoneNumberFlow: Flow<String?> get() = get(PREF_PHONE)
    suspend fun setPhoneNumber(value: String) {
        set(PREF_PHONE, value)
    }

    val pinCodeFlow: Flow<String?> get() = get(PREF_PIN_CODE)
    suspend fun setPinCode(value: String) {
        set(PREF_PIN_CODE, value)
    }

    val isPayedVersionFlow: Flow<Boolean?> get() = get(PREF_IS_PAYED)
    suspend fun setPayedVersion(value: Boolean) {
        set(PREF_IS_PAYED, value)
    }

    private fun <T> get(key: Preferences.Key<T>) = context.dataStore.data.map { it[key] }

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) = context.dataStore.edit {
        it[key] = value
    }
}