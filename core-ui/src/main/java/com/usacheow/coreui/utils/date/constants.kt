package com.usacheow.coreui.utils.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Locale

const val DAYS_OF_WEEK = 7

// val LOCALE get() = Locale.getDefault()
val LOCALE get() = Locale("en")
val RU_LOCALE get() = Locale("ru")
val TODAY get() = LocalDate.now()
val NOW get() = LocalDateTime.now()