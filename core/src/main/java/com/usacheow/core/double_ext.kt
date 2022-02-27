package com.usacheow.core

import com.usacheow.core.date.RU_LOCALE
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

private const val SUM_FORMAT = "%s %s"

fun formatAmount(value: Double, currencySymbol: String) = SUM_FORMAT.format(value.formatDigits(), currencySymbol)

fun Double.formatDigits() = try {
    NumberFormat.getNumberInstance(RU_LOCALE).format(this.round())
} catch (ex: NumberFormatException) {
    this.toString()
}

fun Double.round(places: Int = 2): Double {
    require(places >= 0)
    return BigDecimal.valueOf(this)
        .setScale(places, RoundingMode.HALF_UP)
        .toDouble()
}