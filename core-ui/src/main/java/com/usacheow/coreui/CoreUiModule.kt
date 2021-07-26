package com.usacheow.coreui

import android.app.Application
import com.usacheow.coreui.analytics.AnalyticsTracker
import com.usacheow.coreui.analytics.Tracker
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.resource.ResourcesWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [
    SystemServiceModule::class,
])
@InstallIn(SingletonComponent::class)
class CoreUiModule {

    @Provides
    @Singleton
    fun resources(application: Application): ResourcesWrapper = ResourcesWrapperImpl(application)

    @Provides
    @Singleton
    fun analyticsTracker(application: Application): Tracker = AnalyticsTracker(application)
}