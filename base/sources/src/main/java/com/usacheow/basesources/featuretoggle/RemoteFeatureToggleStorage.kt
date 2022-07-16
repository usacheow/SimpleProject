package com.usacheow.basesources.featuretoggle

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val REMOTE_TOGGLE_FORMAT = "Rft_%s"

class RemoteFeatureToggleStorage @Inject constructor(
    @ApplicationContext context: Context,
) {

    private val preferenceManager by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    private val Feature.storageName get() = REMOTE_TOGGLE_FORMAT.format(key)

    fun clear(items: List<Feature>) = preferenceManager.edit {
        items.forEach { remove(it.storageName) }
    }

    fun isEnabled(feature: Feature): Boolean = preferenceManager.getBoolean(feature.storageName, feature.defaultValue)

    fun set(feature: Feature, isEnabled: Boolean) = preferenceManager.edit {
        putBoolean(feature.storageName, isEnabled)
    }
}