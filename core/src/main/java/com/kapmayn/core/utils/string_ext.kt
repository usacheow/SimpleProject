package com.kapmayn.core.utils

import java.text.NumberFormat
import java.util.Locale

private const val DATE_FORMAT = "%d, %s квартал"
private const val QUARTERS_COUNT = 4

fun String.toRubValue() = NumberFormat.getCurrencyInstance(Locale("ru")).format(this.toDouble()).dropLast(5)

fun String.toRub() = NumberFormat.getCurrencyInstance(Locale("ru")).format(this.toDouble()).dropLast(5) + " \u20BD"

fun Int.toRub() = NumberFormat.getCurrencyInstance(Locale("ru")).format(this).dropLast(5) + " \u20BD"

fun Double.toRub() = NumberFormat.getCurrencyInstance(Locale("ru")).format(this).dropLast(5) + " \u20BD"

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