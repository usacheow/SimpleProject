package com.usacheow.baseanalytics

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AnalyticsModule {

    @Provides
    @Singleton
    fun analyticsTracker(application: Application): com.usacheow.coreui.analytics.Tracker =
        com.usacheow.coreui.analytics.AnalyticsTracker(application)
}