package com.usacheow.showcaseutils.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

interface AnalyticsTracker {

    fun trackEvent(event: Events, clazz: Class<*>, attributes: Map<String, String> = emptyMap())
}

class AnalyticsTrackerImpl(
    private val context: Context,
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

val analyticsDiModule by DI.Module {
    bindSingleton<AnalyticsTracker> { AnalyticsTrackerImpl(instance()) }
}