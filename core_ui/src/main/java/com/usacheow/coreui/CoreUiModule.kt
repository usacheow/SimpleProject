package com.usacheow.coreui

import android.app.Application
import com.usacheow.coreui.analytics.AnalyticsTracker
import com.usacheow.coreui.analytics.Tracker
import com.usacheow.coreui.billing.BillingMediator
import com.usacheow.coreui.billing.BillingWrapper
import com.usacheow.coreui.billing.SimpleBilling
import com.usacheow.coreui.billing.SimpleBillingImpl
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.resource.ResourcesWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreUiModule {

    @Provides
    @Singleton
    fun resources(application: Application): ResourcesWrapper = ResourcesWrapperImpl(application)

    @Provides
    @Singleton
    fun analyticsTracker(application: Application): Tracker = AnalyticsTracker(application)

    @Provides
    @Singleton
    fun simpleBilling(application: Application): SimpleBilling = SimpleBillingImpl(application.applicationContext)

    @Provides
    @Singleton
    fun billingWrapper(simpleBilling: SimpleBilling): BillingWrapper = simpleBilling

    @Provides
    @Singleton
    fun billingMediator(simpleBilling: SimpleBilling): BillingMediator = simpleBilling
}