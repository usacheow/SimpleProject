package com.usacheow.coreui.utils.ext

import com.usacheow.coreui.utils.DateFormat
import java.text.NumberFormat
import java.util.Locale

private const val DATE_FORMAT = "%d, %s квартал"
private const val QUARTERS_COUNT = 4

fun String.normalizedPhoneNumber(): String {
    return filter { it.isDigit() }.substringAfter("7")
}

fun String.toRubValue() = NumberFormat.getNumberInstance(Locale("ru")).format(this.toDouble())

fun String.toRub() = NumberFormat.getNumberInstance(Locale("ru")).format(this.toDouble()) + " \u20BD"

fun Int.toRub() = NumberFormat.getNumberInstance(Locale("ru")).format(this) + " \u20BD"

fun Double.toRub() = NumberFormat.getNumberInstance(Locale("ru")).format(this) + " \u20BD"

fun String.toSquare() = "$this м²"

fun Double.toSquare() = "$this м²"

fun String?.getRomanDateWithQuarter(): String {
    val (year, month) = this?.toDate(DateFormat.yyyy__MM__dd)?.let {
        it.parseTo(DateFormat.yyyy).toInt() to it.parseTo(DateFormat.mm).toInt()
    } ?: 1 to 1
    val romanMonth = when (month / QUARTERS_COUNT) {
        0 -> "I"
        1 -> "II"
        2 -> "III"
        else -> "IV"
    }
    return DATE_FORMAT.format(year, romanMonth)
}