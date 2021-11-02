package com.usacheow.coredata.featuretoggle

import com.usacheow.coredata.BuildConfig
import javax.inject.Inject

class FeatureToggleImpl @Inject constructor(
    private val remoteFeatureToggleStorage: RemoteFeatureToggleStorage,
    private val manualFeatureToggleStorage: ManualFeatureToggleStorage,
) : EditableFeatureToggle {

    override fun isEnabled(feature: Feature): Boolean {
        return when (val isManualToggleEnabled = isManualEnabled(feature)) {
            BuildConfig.DEBUG && isManualToggleEnabled != null -> isManualToggleEnabled

            else -> isRemoteEnabled(feature)
        }
    }

    override fun isRemoteEnabled(feature: Feature): Boolean = remoteFeatureToggleStorage.isEnabled(feature)

    override fun isManualEnabled(feature: Feature): Boolean? = manualFeatureToggleStorage.isEnabled(feature)

    override fun setRemoteValue(feature: Feature, value: Boolean) = remoteFeatureToggleStorage.set(feature, value)

    override fun setManualValue(feature: Feature, value: Boolean?) = manualFeatureToggleStorage.set(feature, value)

    override fun clearRemoteValues() {
        remoteFeatureToggleStorage.clear(Feature.values().toList())
    }
}