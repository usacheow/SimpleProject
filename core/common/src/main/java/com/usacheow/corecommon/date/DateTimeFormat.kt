package com.usacheow.corecommon.date

import com.usacheow.corecommon.strings.DEFAULT_LOCALE
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class DateTimeFormat(val code: String) {

    ISO_LOCAL_DATE("yyyy-MM-dd"),

    ISO_LOCAL_TIME("HH:mm:ss"),

    ISO_LOCAL_DATE_TIME("yyyy-MM-dd'T'HH:mm"),

    ISO_LOCAL_DATE_TIME_FULL("yyyy-MM-dd'T'HH:mm[:ss][.SSS]"),
    // XXX - 03:00
    ISO_OFFSET_DATE_TIME("yyyy-MM-dd'T'HH:mm[:ss][.SSS][XXX]"),
    // Z - 0300
    ISO_ZONED_DATE_TIME("yyyy-MM-dd'T'HH:mm[:ss][.SSS]Z");

    fun toDateTimeFormat(locale: Locale = DEFAULT_LOCALE) = code.toDateTimeFormat(locale)
}

fun String.toDateTimeFormat(locale: Locale = DEFAULT_LOCALE) = kotlin.runCatching {
    DateTimeFormatter.ofPattern(this, locale)
}.getOrNull()