package com.usacheow.basesources.featuretoggle

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.usacheow.coredata.BuildConfig
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

private val FETCH_TIME_DURATION_DEBUG = 0.hours
private val FETCH_TIME_DURATION_RELEASE = 6.hours

class FeatureToggleUpdater @Inject constructor(
    private val featureToggle: EditableFeatureToggle,
) {

    private val firebaseRemoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

    init {
        initDefaultValues()
        saveRemoteValues()
    }

    private fun initDefaultValues() {
        Feature.values()
            .map { it.key to it.defaultValue }
            .toMap()
            .also { firebaseRemoteConfig.setDefaultsAsync(it) }
    }

    private fun saveRemoteValues() {
        firebaseRemoteConfig.activate()
        firebaseRemoteConfig.fetch(
            when {
                BuildConfig.DEBUG -> FETCH_TIME_DURATION_DEBUG
                else -> FETCH_TIME_DURATION_RELEASE
            }.inWholeMilliseconds
        ).addOnSuccessListener {
            featureToggle.clearRemoteValues()
            Feature.values().forEach {
                featureToggle.setRemoteValue(it, firebaseRemoteConfig.getBoolean(it.key))
            }
        }
    }
}