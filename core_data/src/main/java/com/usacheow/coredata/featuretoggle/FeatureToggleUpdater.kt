package com.usacheow.coredata.featuretoggle

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.usacheow.coredata.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val FETCH_TIME_HOURS_DEBUG = 0L
private const val FETCH_TIME_HOURS_RELEASE = 6L

class FeatureToggleUpdater
@Inject constructor(
    private val featureToggle: EditableFeatureToggle
) {

    private val firebaseRemoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

    init {
        val defaultValues = Feature.values().map { it.key to it.defaultValue }.toMap()
        firebaseRemoteConfig.setDefaultsAsync(defaultValues)

        val remoteConfigFetchTask = firebaseRemoteConfig.fetch(when {
            BuildConfig.DEBUG -> FETCH_TIME_HOURS_DEBUG
            else -> TimeUnit.HOURS.toSeconds(FETCH_TIME_HOURS_RELEASE)
        })
        remoteConfigFetchTask.addOnSuccessListener {
            firebaseRemoteConfig.activate()

            val enabledFeatures = Feature.values()
                .filter { firebaseRemoteConfig.getBoolean(it.key) }
            featureToggle.enable(enabledFeatures)
        }
    }
}