package com.kapmayn.core.analytics

import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsTrackerHolder {

    private var tracker: FirebaseAnalytics? = null

    fun init(tracker: FirebaseAnalytics) {
        this.tracker = tracker
    }

    fun getInstance(): FirebaseAnalytics {
        if (tracker == null) {
            throw IllegalStateException("Don't init tracker")
        }
        return tracker as FirebaseAnalytics
    }
}