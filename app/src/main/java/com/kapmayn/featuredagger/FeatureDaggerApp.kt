package com.kapmayn.featuredagger

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.kapmayn.core.analytics.AnalyticsTrackerHolder
import com.kapmayn.diproviders.DiApp
import com.kapmayn.diproviders.DiProvider
import com.kapmayn.featuredagger.di.AppComponent

class FeatureDaggerApp : Application(), DiApp {

    override val diProvider: DiProvider by lazy { AppComponent.init(this) }

    override fun onCreate() {
        super.onCreate()

        val tracker = FirebaseAnalytics.getInstance(this).apply {
            setUserId("hasn't id")
            setUserProperty("category", "developer")
        }
        AnalyticsTrackerHolder.init(tracker)
        (diProvider as AppComponent).inject(this)
    }
}