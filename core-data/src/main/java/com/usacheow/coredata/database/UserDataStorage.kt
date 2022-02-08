package com.usacheow.coredata.database

import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

private val PREF_NAME = stringPreferencesKey("PREF_NAME")
private val PREF_PHONE = stringPreferencesKey("PREF_PHONE")

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
}