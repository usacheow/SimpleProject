package com.usacheow.coredata.featuretoggle

import com.usacheow.corecommon.model.BuildInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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