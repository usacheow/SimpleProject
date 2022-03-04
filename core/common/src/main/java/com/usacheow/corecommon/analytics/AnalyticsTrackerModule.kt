package com.usacheow.corecommon.analytics

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AnalyticsTrackerModule {

    @Binds
    @Singleton
    fun analyticsTracker(tracker: AnalyticsTrackerImpl): AnalyticsTracker
}