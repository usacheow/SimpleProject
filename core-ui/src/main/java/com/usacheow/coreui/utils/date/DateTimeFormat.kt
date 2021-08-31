package com.usacheow.coreui.utils.date

import java.time.format.DateTimeFormatter
import java.util.*

enum class DateTimeFormat(val code: String) {

    ISO_LOCAL_DATE("yyyy-MM-dd"),

    ISO_LOCAL_TIME("HH:mm:ss"),

    ISO_LOCAL_DATE_TIME("yyyy-MM-dd'T'HH:mm[:ss][.SSS]"),
    //XXX - 03:00
    ISO_OFFSET_DATE_TIME("yyyy-MM-dd'T'HH:mm[:ss][.SSS][XXX]"),
    //Z - 0300
    ISO_ZONED_DATE_TIME("yyyy-MM-dd'T'HH:mm[:ss][.SSS]Z");

    fun toDateTimeFormat(locale: Locale = LOCALE) = code.toDateTimeFormat(locale)
}

fun String.toDateTimeFormat(locale: Locale = LOCALE) = kotlin.runCatching {
    DateTimeFormatter.ofPattern(this, locale)
}.getOrNull()