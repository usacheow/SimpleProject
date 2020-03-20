package com.usacheow.core.analytics

import android.os.Bundle

interface ITracker {

    fun trackEvent(event: Events, bundle: Bundle? = null)
}