package com.usacheow.core

import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
object DurationValue {
    val SECOND = 1.seconds
    val MINUTES = 1.minutes
    val HOURS = 1.hours
    val DAY = 1.days
}