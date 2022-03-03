package com.usacheow.corecommon

import com.usacheow.corecommon.analytics.AnalyticsTracker
import com.usacheow.corecommon.analytics.AnalyticsTrackerImpl
import com.usacheow.corecommon.resource.ResourcesWrapper
import com.usacheow.corecommon.resource.ResourcesWrapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun resources(resources: ResourcesWrapperImpl): ResourcesWrapper

    @Binds
    @Singleton
    fun analyticsTracker(tracker: AnalyticsTrackerImpl): AnalyticsTracker
}