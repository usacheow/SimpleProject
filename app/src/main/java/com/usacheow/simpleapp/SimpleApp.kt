package com.usacheow.simpleapp

import android.app.Application
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Tracker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SimpleApp : Application() {

    //todo: add firebase config json
    //move to another place (e.g. start activity)
    //when appmetrica is enabled
    //https://github.com/yandexmobile/metrica-sdk-android/issues/76
    @Inject lateinit var tracker: Tracker

//    @Inject lateinit var featureToggleUpdater: FeatureToggleUpdater

    override fun onCreate() {
        super.onCreate()

        AnalyticsTrackerHolder.init(tracker)
    }
}