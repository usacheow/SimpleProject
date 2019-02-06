package com.kapmayn.core.analytics

interface ITracker {

    fun trackEvent(screen: IScreen, message: String)

    fun trackEvent(screen: IScreen, message: String, event: Events)
}