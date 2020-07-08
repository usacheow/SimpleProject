package com.usacheow.simpleapp.di

import android.app.Application
import android.content.Context
import com.usacheow.core.ResourcesWrapper
import com.usacheow.core.ResourcesWrapperImpl
import com.usacheow.core.analytics.ITracker
import com.usacheow.di.ApplicationScope
import com.usacheow.simpleapp.AnalyticsTracker
import dagger.Module
import dagger.Provides

@Module
class CoreModule(
    private val application: Application
) {

    @Provides
    @ApplicationScope
    fun provideApplication() = application

    @Provides
    @ApplicationScope
    fun provideContext(): Context = application

    @Provides
    @ApplicationScope
    fun provideResources(): ResourcesWrapper = ResourcesWrapperImpl(application)

    @Provides
    @ApplicationScope
    fun provideTracker(): ITracker = AnalyticsTracker(application)

    //todo take token from play console
//    @Provides
//    @ApplicationScope
//    fun provideBilling(): Billing = BillingWrapper.initBilling(application)
}