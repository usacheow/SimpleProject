package com.kapmayn.core.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.kapmayn.core.utils.bundleOf

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

fun FirebaseAnalytics.trackEvent(screen: IScreen, message: String) {
    val bundle = bundleOf {
        putString("class_name", screen.getClassName())
        putString("screen_name", screen.getScreenName())
    }
    logEvent(message, bundle)
}