package com.usacheow.corecommon.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface AnalyticsTracker {

    fun trackEvent(event: Events, clazz: Class<*>, attributes: Map<String, String> = emptyMap())
}

class AnalyticsTrackerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AnalyticsTracker {

    private val firebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(context)
    }

    override fun trackEvent(event: Events, clazz: Class<*>, attributes: Map<String, String>) {
//        val bundle = bundleOf(*attributes.map { it.key to it.value }.toTypedArray())
//
//        firebaseAnalytics.logEvent("${event.value} ${clazz.simpleName}", bundle)
    }
}

enum class Events(val value: String) {
    START_SCREEN("START_SCREEN"),
    STOP_SCREEN("STOP_SCREEN")
}