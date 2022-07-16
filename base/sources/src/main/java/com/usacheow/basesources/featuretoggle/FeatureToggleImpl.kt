package com.usacheow.basesources.featuretoggle

import com.usacheow.corecommon.model.BuildInfo
import javax.inject.Inject

class FeatureToggleImpl @Inject constructor(
    private val remoteFeatureToggleStorage: RemoteFeatureToggleStorage,
    private val localFeatureToggleStorage: LocalFeatureToggleStorage,
    private val buildInfo: BuildInfo,
) : EditableFeatureToggle {

    override fun isEnabled(feature: Feature): Boolean {
        val isManualToggleEnabled = isLocalEnabled(feature)
        return when {
            !buildInfo.isRelease && isManualToggleEnabled != null -> isManualToggleEnabled
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