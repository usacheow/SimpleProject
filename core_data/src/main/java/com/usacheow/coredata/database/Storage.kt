package com.usacheow.coredata.database

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

private const val PREF_TOKEN = "PREF_TOKEN"
private const val PREF_IS_FIRST_ENTRY = "PREF_IS_FIRST_ENTRY"
private const val PREF_PHONE = "PREF_PHONE"
private const val PREF_NAME = "PREF_NAME"
private const val PREF_PIN_CODE = "PREF_PIN_CODE"
private const val PREF_ALLOW_FINGERPRINT = "PREF_ALLOW_FINGERPRINT"

class Storage
@Inject constructor(
    private val context: Context
) {

    var userName: String
        get() = getPreferences().getString(PREF_NAME, "")!!
        set(value) {
            getPreferencesEditor().putString(PREF_NAME, value).apply()
        }

    var phoneNumber: String
        get() = getPreferences().getString(PREF_PHONE, "")!!
        set(value) {
            getPreferencesEditor().putString(PREF_PHONE, value).apply()
        }

    var isFirstEntry: Boolean
        get() = getPreferences().getBoolean(PREF_IS_FIRST_ENTRY, true)
        set(value) {
            getPreferencesEditor().putBoolean(PREF_IS_FIRST_ENTRY, value).apply()
        }

    var token: String
        get() = getPreferences().getString(PREF_TOKEN, "")!!
        set(value) {
            getPreferencesEditor().putString(PREF_TOKEN, value).apply()
        }

    var pinCode: String
        get() = getPreferences().getString(PREF_PIN_CODE, "")!!
        set(value) {
            getPreferencesEditor().putString(PREF_PIN_CODE, value).apply()
        }

    var isAllowFingerprint: Boolean
        get() = getPreferences().getBoolean(PREF_ALLOW_FINGERPRINT, true)
        set(value) {
            getPreferencesEditor().putBoolean(PREF_ALLOW_FINGERPRINT, value).apply()
        }

    fun clearPreferences() {
        getPreferencesEditor().clear().apply()
    }

    private fun getPreferencesEditor() = getPreferences().edit()

    private fun getPreferences() = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
}