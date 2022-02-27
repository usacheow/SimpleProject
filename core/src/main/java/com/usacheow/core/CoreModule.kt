package com.usacheow.core

import com.usacheow.core.analytics.AnalyticsTracker
import com.usacheow.core.analytics.AnalyticsTrackerImpl
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.core.resource.ResourcesWrapperImpl
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