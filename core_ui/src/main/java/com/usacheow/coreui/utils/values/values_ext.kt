package com.usacheow.coreui.utils.values

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