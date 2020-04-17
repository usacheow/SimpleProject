package com.usacheow.simpleapp

import android.app.Application
import com.usacheow.core.analytics.AnalyticsTrackerHolder
import com.usacheow.core.analytics.ITracker
import com.usacheow.diprovider.DiApp
import com.usacheow.diprovider.DiProvider
import com.usacheow.simpleapp.di.AppComponent
import javax.inject.Inject

class SimpleApp : Application(), DiApp {

    //todo: add firebase config json
    //move to another place (e.g. start activity)
    //when appmetrica is enabled
    //https://github.com/yandexmobile/metrica-sdk-android/issues/76
//    @Inject lateinit var featureToggleUpdater: FeatureToggleUpdater
    @Inject lateinit var tracker: ITracker

    //todo take token from play console
//    @Inject override lateinit var billing: Billing
    override val diProvider: DiProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        (diProvider as AppComponent).inject(this)
        AnalyticsTrackerHolder.init(tracker)
    }
}