package com.usacheow.coredata

import android.content.Context
import android.content.SharedPreferences
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
        get() = getApplicationPreferences().getString(PREF_NAME, "")!!
        set(value) {
            getApplicationEditor().putString(PREF_NAME, value).apply()
        }

    var phoneNumber: String
        get() = getApplicationPreferences().getString(PREF_PHONE, "")!!
        set(value) {
            getApplicationEditor().putString(PREF_PHONE, value).apply()
        }

    var isFirstEntry: Boolean
        get() = getApplicationPreferences().getBoolean(PREF_IS_FIRST_ENTRY, true)
        set(value) {
            getApplicationEditor().putBoolean(PREF_IS_FIRST_ENTRY, value).apply()
        }

    var token: String
        get() = getApplicationPreferences().getString(PREF_TOKEN, "")!!
        set(value) {
            getApplicationEditor().putString(PREF_TOKEN, value).apply()
        }

    var pinCode: String
        get() = getApplicationPreferences().getString(PREF_PIN_CODE, "")!!
        set(value) {
            getApplicationEditor().putString(PREF_PIN_CODE, value).apply()
        }

    var isAllowFingerprint: Boolean
        get() = getApplicationPreferences().getBoolean(PREF_ALLOW_FINGERPRINT, true)
        set(value) {
            getApplicationEditor().putBoolean(PREF_ALLOW_FINGERPRINT, value).apply()
        }

    fun clearPreferences() {
        getApplicationEditor().clear().apply()
    }

    private fun getApplicationEditor(): SharedPreferences.Editor = getApplicationPreferences().edit()

    private fun getApplicationPreferences() = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
}