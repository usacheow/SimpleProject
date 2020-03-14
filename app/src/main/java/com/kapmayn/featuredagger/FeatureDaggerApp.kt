package com.kapmayn.featuredagger

import android.app.Application
import com.kapmayn.core.DiApp
import com.kapmayn.core.analytics.AnalyticsTrackerHolder
import com.kapmayn.core.provider.DiProvider
import com.kapmayn.featuredagger.di.AppComponent

class FeatureDaggerApp : Application(), DiApp {

    override val diProvider: DiProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        val tracker = AnalyticsTracker(this)
        AnalyticsTrackerHolder.init(tracker)
        (diProvider as AppComponent).inject(this)
    }
}