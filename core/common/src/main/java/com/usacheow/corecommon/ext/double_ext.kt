package com.usacheow.corecommon.ext

import com.usacheow.corecommon.strings.RU_LOCALE
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

fun Double.formatToMoney() = try {
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