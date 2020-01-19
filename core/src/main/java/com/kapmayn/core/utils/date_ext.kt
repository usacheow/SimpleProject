package com.kapmayn.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class DateFormat(val code: String) {
    dd("dd"),
    mm("mm"),
    yyyy("yyyy"),
    yy("yy"),
    HH_mm("HH:mm"),
    yyyy__MM__dd("yyyy-MM-dd"),
    yyyy__MM("yyyy-MM"),
    dd___MM("dd.MM"),
    MM__yyyy("MM ''yyyy"),
    MMMM__yy("MMMM ''yy"),
    MM("MM"),
    MMMM("MMMM"),
    d_MMMM("d MMMM"),
    dd__MMMM__yyyy("dd.MM.yyyy"),
    dd_MMMM_yyyy("dd MMMM yyyy"),
    yyyy__MM__dd_hh_mm_ss("yyyy-MM-dd HH:mm:ss")
}

fun Long.parseTo(format: DateFormat): String {
    val dateFormat = SimpleDateFormat(format.code, Locale("ru"))
    return dateFormat.format(this)
}

fun Long.parseTo(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.parseTo(format: DateFormat): String {
    val dateFormat = SimpleDateFormat(format.code, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.parseTo(format: String): String {
    val dateFormat = SimpleDateFormat(format, Locale("ru"))
    return dateFormat.format(this)
}

fun String.toDate(format: DateFormat): Date {
    val dateFormat = SimpleDateFormat(format.code, Locale("ru"))
    return dateFormat.parse(this)
}

fun String.toDate(format: String): Date {
    val dateFormat = SimpleDateFormat(format, Locale("ru"))
    return dateFormat.parse(this)
}

fun getMonthName() = Date().parseTo(DateFormat.MM).toInt().monthName

val String.monthName get() = this.toIntOrNull().monthName

val Int?.monthName
    get() = when (this) {
        1 -> "Январь"
        2 -> "Февраль"
        3 -> "Март"
        4 -> "Апрель"
        5 -> "Май"
        6 -> "Июнь"
        7 -> "Июль"
        8 -> "Август"
        9 -> "Сентябрь"
        10 -> "Октябрь"
        11 -> "Ноябрь"
        12 -> "Декабрь"
        else -> ""
    }