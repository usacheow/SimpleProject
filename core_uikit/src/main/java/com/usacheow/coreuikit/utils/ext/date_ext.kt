package com.usacheow.coreuikit.utils.ext

import com.usacheow.coreuikit.utils.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun TODAY() = Calendar.getInstance(Locale.getDefault()).time

fun Date.isToday(): Boolean {
    return TODAY().parseTo(DateFormat.dd__MM__yyyy) == parseTo(DateFormat.dd__MM__yyyy)
}

fun Date.parseTo(format: DateFormat) = parseTo(format.code)

fun Date.parseTo(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}

fun Date.toCalendar(
    year: Int = parseTo(DateFormat.yyyy).toInt(),
    month: Int = parseTo(DateFormat.MM).toInt() - 1,
    day: Int = parseTo(DateFormat.dd).toInt()
) = Calendar.getInstance(Locale.getDefault()).apply {
    set(year, month, day)
}!!

fun Calendar.copy(): Calendar {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.set(
        get(Calendar.YEAR),
        get(Calendar.MONTH),
        get(Calendar.DAY_OF_MONTH)
    )
    return calendar
}

fun Long.parseTo(format: DateFormat) = parseTo(format.code)

fun Long.parseTo(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this)
}

fun Long.toDate(): Date {
    val dateFormat = SimpleDateFormat(DateFormat.yyyy__MM__dd.code, Locale.getDefault())
    return dateFormat.parse(dateFormat.format(this))
}

fun Long.toCalendar() = toDate().toCalendar()

fun String.toDate(format: DateFormat) = toDate(format.code)

fun String.toDate(format: String): Date {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.parse(this)
}