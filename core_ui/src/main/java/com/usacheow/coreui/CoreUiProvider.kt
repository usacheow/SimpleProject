package com.usacheow.coreui

import android.app.Application
import android.content.Context
import com.usacheow.coreui.analytics.Tracker
import com.usacheow.coreui.resources.ResourcesWrapper

interface CoreUiProvider {

    fun provideApplication(): Application

    fun provideContext(): Context

    fun provideResources(): ResourcesWrapper

    fun provideTracker(): Tracker

    //todo take token from play console
//    fun provideBilling(): Billing
}