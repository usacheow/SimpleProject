package com.usacheow.basesources.featuretoggle

import com.usacheow.corecommon.model.BuildInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

interface FeatureToggle {

    fun isEnabled(feature: Feature): Boolean

    fun isRemoteEnabled(feature: Feature): Boolean

    fun isLocalEnabled(feature: Feature): Boolean?
}

interface EditableFeatureToggle : FeatureToggle {

    fun setRemoteValue(feature: Feature, value: Boolean)

    fun setLocalValue(feature: Feature, value: Boolean?)

    fun clearRemoteValues()
}

@Module
@InstallIn(SingletonComponent::class)
class FeatureToggleModule {

    @Provides
    @Singleton
    fun editableFeatureToggle(
        remoteFeatureToggleStorage: RemoteFeatureToggleStorage,
        localFeatureToggleStorage: LocalFeatureToggleStorage,
        buildInfo: BuildInfo,
    ): EditableFeatureToggle = FeatureToggleImpl(remoteFeatureToggleStorage, localFeatureToggleStorage, buildInfo)

    @Provides
    @Singleton
    fun featureToggle(featureToggle: EditableFeatureToggle): FeatureToggle = featureToggle
}