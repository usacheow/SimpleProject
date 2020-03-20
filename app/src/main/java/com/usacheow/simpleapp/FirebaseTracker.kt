package com.usacheow.simpleapp

import android.app.Application
import android.os.Bundle
import com.usacheow.core.analytics.Events
import com.usacheow.core.analytics.ITracker
import javax.inject.Inject

class FirebaseTracker
@Inject constructor(
    private val application: Application
) : ITracker {

    //todo: add firebase config json
//    private val analytics by lazy {
//        FirebaseAnalytics.getInstance(application)
//    }

    override fun trackEvent(event: Events, bundle: Bundle?) {
//        analytics.logEvent(event.value, bundle)
    }
}