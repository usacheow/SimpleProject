package com.kapmayn.core.analytics

interface ITracker {

    fun trackEvent(screen: IScreen, message: String)

    fun trackEvent(message: String, event: Events)

    fun trackEvent(screen: IScreen, message: String, event: Events)
}