package com.usacheow.coreui.analytics

interface Tracker {

    fun trackEvent(event: Events, clazz: Class<*>, attributes: Map<String, String> = emptyMap())
}

enum class Events(val value: String) {
    START_SCREEN("START_SCREEN"),
    STOP_SCREEN("STOP_SCREEN")
}