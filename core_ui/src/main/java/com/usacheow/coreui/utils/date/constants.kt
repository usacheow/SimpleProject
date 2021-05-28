package com.usacheow.coreui.utils.values

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

const val DAYS_OF_WEEK = 7

//fun LOCALE() = Locale.getDefault()
fun LOCALE() = Locale("en")
fun RU_LOCALE() = Locale("ru")
fun TODAY() = LocalDate.now()
fun NOW() = LocalDateTime.now()
fun NOW_TIME() = LocalTime.now()
