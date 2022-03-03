package com.usacheow.coredata.featuretoggle

import com.usacheow.coredata.BuildConfig
import javax.inject.Inject

class FeatureToggleImpl @Inject constructor(
    private val remoteFeatureToggleStorage: RemoteFeatureToggleStorage,
    private val localFeatureToggleStorage: LocalFeatureToggleStorage,
) : EditableFeatureToggle {

    override fun isEnabled(feature: Feature): Boolean {
        return when (val isManualToggleEnabled = isLocalEnabled(feature)) {
            BuildConfig.DEBUG && isManualToggleEnabled != null -> isManualToggleEnabled

            else -> isRemoteEnabled(feature)
        }
    }

    override fun isRemoteEnabled(feature: Feature): Boolean = remoteFeatureToggleStorage.isEnabled(feature)

    override fun isLocalEnabled(feature: Feature): Boolean? = localFeatureToggleStorage.isEnabled(feature)

    override fun setRemoteValue(feature: Feature, value: Boolean) = remoteFeatureToggleStorage.set(feature, value)

    override fun setLocalValue(feature: Feature, value: Boolean?) = localFeatureToggleStorage.set(feature, value)

    override fun clearRemoteValues() {
        remoteFeatureToggleStorage.clear(Feature.values().toList())
    }
}