package com.kapmayn.core.analytics

object AnalyticsTrackerHolder {

    private var tracker: ITracker? = null

    fun init(tracker: ITracker) {
        this.tracker = tracker.init()
    }

    fun getInstance(): ITracker {
        if (tracker == null) {
            throw IllegalStateException("Don't init tracker")
        }
        return tracker as ITracker
    }
}