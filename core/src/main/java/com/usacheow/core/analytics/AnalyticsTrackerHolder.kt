package com.usacheow.core.analytics

object AnalyticsTrackerHolder {

    private var tracker: ITracker? = null

    fun init(tracker: ITracker) {
        this.tracker = tracker
    }

    fun getInstance(): ITracker {
        checkNotNull(tracker) { "Don't init tracker" }
        return tracker as ITracker
    }
}