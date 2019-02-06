package com.kapmayn.featuredagger

import android.app.Application
import com.kapmayn.core.analytics.Events
import com.kapmayn.core.analytics.IScreen
import com.kapmayn.core.analytics.ITracker

class AnalyticsTracker(
    private val application: Application
) : ITracker {

    override fun trackEvent(screen: IScreen, message: String) {

    }

    override fun trackEvent(screen: IScreen, message: String, event: Events) {

    }
}