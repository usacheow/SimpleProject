package com.usacheow.coreuikit.utils.ext

import com.usacheow.coreuikit.utils.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun LOCALE() = Locale.getDefault()
fun TODAY() = Calendar.getInstance(LOCALE()).time
fun Date.isToday(): Boolean {
    return TODAY().parseTo(DateFormat.dd__MM__yyyy) == parseTo(DateFormat.dd__MM__yyyy)
}

/*
* Parse date to String by DateFormat
* */
fun Date.parseTo(format: DateFormat) = format(format.code)

fun Date.parseTo(format: String) = format(format)
fun Long.parseTo(format: DateFormat) = format(format.code)
fun Long.parseTo(format: String) = format(format)

private fun Any.format(format: String): String {
    val dateFormat = SimpleDateFormat(format, LOCALE())
    return dateFormat.format(this)
}

/*
* Convert date in DateFormat to Date
* */
fun String.toDate(format: DateFormat) = toDate(format.code)

fun String.toDate(format: String): Date {
    val dateFormat = SimpleDateFormat(format, LOCALE())
    return dateFormat.parse(this)
}

fun Long.toDate(): Date {
    val dateFormat = SimpleDateFormat(DateFormat.yyyy__MM__dd.code, LOCALE())
    return dateFormat.parse(dateFormat.format(this))
}

/*
* Convert date in DateFormat to Calendar
* */
fun Long.toCalendar() = toDate().toCalendar()
fun Date.toCalendar(
    year: Int = parseTo(DateFormat.yyyy).toInt(),
    month: Int = parseTo(DateFormat.MM).toInt() - 1,
    day: Int = parseTo(DateFormat.dd).toInt()
) = Calendar.getInstance(LOCALE()).apply {
    set(year, month, day)
}!!