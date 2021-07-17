package com.usacheow.coreui.analytics

object AnalyticsTrackerHolder {

    private var tracker: Tracker? = null

    fun init(tracker: Tracker) {
        AnalyticsTrackerHolder.tracker = tracker
    }

    fun getInstance(): Tracker? = tracker
}