package com.usacheow.coredata.database

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_TOKEN = "PREF_TOKEN"

@Singleton
class TokenStorage
@Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val preferenceManager by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    var token: String
        get() = preferenceManager.getString(PREF_TOKEN, "") ?: ""
        set(value) = preferenceManager.edit {
            putString(PREF_TOKEN, value)
        }
}