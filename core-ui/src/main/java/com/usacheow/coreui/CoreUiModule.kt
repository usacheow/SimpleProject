package com.usacheow.coreui

import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.core.resource.ResourcesWrapperImpl
import com.usacheow.coreui.analytics.AnalyticsTracker
import com.usacheow.coreui.analytics.AnalyticsTrackerImpl
import com.usacheow.coreui.utils.NotificationHelper
import com.usacheow.coreui.utils.NotificationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [
    SystemServiceModule::class,
])
@InstallIn(SingletonComponent::class)
interface CoreUiModule {

    @Binds
    @Singleton
    fun resources(resources: ResourcesWrapperImpl): ResourcesWrapper

    @Binds
    @Singleton
    fun analyticsTracker(tracker: AnalyticsTrackerImpl): AnalyticsTracker

    @Binds
    @Singleton
    fun notificationHelper(helper: NotificationHelperImpl): NotificationHelper
}