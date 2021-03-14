package com.usacheow.coreui.utils.date

import com.usacheow.coreui.utils.values.LOCALE
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

object DateFactory {

    fun from(milliseconds: Long): Date {
        val dateFormat = SimpleDateFormat(DateFormat.yyyy__MM__dd_T_hh_mm_ss.code, LOCALE())
        return dateFormat.parse(dateFormat.format(milliseconds))!!
    }

    fun from(date: String, format: String): Date {
        val dateFormat = SimpleDateFormat(format, LOCALE())
        return dateFormat.parse(date)!!
    }

    fun from(date: String, format: DateFormat) = from(date, format.code)
}

fun Date.toCalendar(
    year: Int = parseTo(DateFormat.yyyy).toInt(),
    month: Int = parseTo(DateFormat.MM).toInt() - 1,
    day: Int = parseTo(DateFormat.dd).toInt()
) = Calendar.getInstance(LOCALE()).apply {
    set(year, month, day)
}

fun Date.toLocalDateTime() = LocalDateTime.parse(parseTo(DateFormat.yyyy__MM__dd_T_hh_mm_ss))

fun Date.parseTo(format: DateFormat) = format(format.code)

fun Date.parseTo(format: String) = format(format)

private fun Date.format(format: String): String {
    val dateFormat = SimpleDateFormat(format, LOCALE())
    return dateFormat.format(this)
}