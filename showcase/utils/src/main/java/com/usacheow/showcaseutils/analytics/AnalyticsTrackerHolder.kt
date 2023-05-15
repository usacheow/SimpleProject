package com.usacheow.showcaseutils.analytics

object AnalyticsTrackerHolder {

    private var tracker: AnalyticsTracker? = null

    fun init(tracker: AnalyticsTracker) {
        AnalyticsTrackerHolder.tracker = tracker
    }

    fun getInstance(): AnalyticsTracker? = tracker
}