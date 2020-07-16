package com.usacheow.simpleapp.di

import android.app.Application
import android.content.Context
import com.usacheow.coreui.analytics.Tracker
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.resources.ResourcesWrapperImpl
import com.usacheow.simpleapp.AnalyticsTracker
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class CoreModule(
    private val application: Application
) {

    @Provides
    @Reusable
    fun application() = application

    @Provides
    @Reusable
    fun context(): Context = application

    @Provides
    @Reusable
    fun resources(): ResourcesWrapper = ResourcesWrapperImpl(application)

    @Provides
    @Reusable
    fun analyticsTracker(): Tracker = AnalyticsTracker(application)

    //todo take token from play console
//    @Provides
//    @Reusable
//    fun billing(): Billing = BillingWrapper.initBilling(application)
}