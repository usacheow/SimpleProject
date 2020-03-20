package com.usacheow.coredata.featuretoggle

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

private const val SETTINGS_NAME = "FeatureToggleImpl"

class FeatureToggleStorage
@Inject constructor(
    context: Context
) {

    private val preferences = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)

    fun clear() = preferences.change { clear() }

    fun clear(feature: Feature) = preferences.change { remove(feature.toString()) }

    fun isEnabled(feature: Feature): Boolean = preferences.getBoolean(feature.toString(), feature.defaultValue)

    fun set(feature: Feature, isEnabled: Boolean) = preferences.change {
        putBoolean(feature.toString(), isEnabled)
    }

    fun isContains(feature: Feature): Boolean = preferences.contains(feature.toString())
}

fun SharedPreferences.change(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}