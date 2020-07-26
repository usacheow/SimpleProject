package com.usacheow.coreui

import android.app.Application
import com.usacheow.coreui.analytics.AnalyticsTracker
import com.usacheow.coreui.analytics.Tracker
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.resources.ResourcesWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CoreUiModule {

    @Provides
    @Singleton
    fun resources(application: Application): ResourcesWrapper = ResourcesWrapperImpl(application)

    @Provides
    @Singleton
    fun analyticsTracker(application: Application): Tracker = AnalyticsTracker(application)

    //todo take token from play console
//    @Provides
//    @Singleton
//    fun billing(): Billing = BillingWrapper.initBilling(application)
}