package com.usacheow.core.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

const val DAYS_OF_WEEK = 7

val LOCALE get() = Locale("en")
val RU_LOCALE get() = Locale("ru")
val TODAY get() = LocalDate.now()
val NOW get() = LocalDateTime.now()