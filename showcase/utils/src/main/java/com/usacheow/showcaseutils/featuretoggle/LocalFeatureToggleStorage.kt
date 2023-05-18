package com.usacheow.showcaseutils.featuretoggle

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

private const val MANUAL_TOGGLE_FORMAT = "Mft_%s"

class LocalFeatureToggleStorage(context: Context) {

    private val preferenceManager by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    private val Feature.storageName get() = MANUAL_TOGGLE_FORMAT.format(key)

    fun isEnabled(feature: Feature): Boolean? {
        return if (preferenceManager.contains(feature.storageName)) {
            preferenceManager.getBoolean(feature.storageName, true)
        } else {
            null
        }
    }

    fun set(feature: Feature, isEnabled: Boolean?) = preferenceManager.edit {
        if (isEnabled == null) {
            remove(feature.storageName)
        } else {
            putBoolean(feature.storageName, isEnabled)
        }
    }
}