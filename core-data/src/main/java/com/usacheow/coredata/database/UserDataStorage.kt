package com.usacheow.coredata.database

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

private val PREF_NAME = stringPreferencesKey("PREF_NAME")
private val PREF_PHONE = stringPreferencesKey("PREF_PHONE")
private val PREF_IS_PAYED = booleanPreferencesKey("PREF_IS_PAYED")

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

    val isPayedVersionFlow: Flow<Boolean?> get() = provider.userDataStore.get(PREF_IS_PAYED)
    suspend fun setPayedVersion(value: Boolean) {
        provider.userDataStore.set(PREF_IS_PAYED, value)
    }
}