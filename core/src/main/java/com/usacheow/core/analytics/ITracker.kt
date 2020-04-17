package com.usacheow.core.analytics

interface ITracker {

    fun trackEvent(event: Events, attributes: Map<String, String> = emptyMap())
}