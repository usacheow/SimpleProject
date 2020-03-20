package com.usacheow.diprovider

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.usacheow.core.analytics.ITracker

interface CoreProvider {

    fun provideApplication(): Application

    fun provideContext(): Context

    fun provideResources(): Resources

    fun provideTracker(): ITracker

    //todo take token from play console
//    fun provideBilling(): Billing
}