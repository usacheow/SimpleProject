package com.usacheow.core.date

import java.text.SimpleDateFormat
import java.time.LocalDateTime

object LocalDateTimeFactory {

    fun from(milliseconds: Long) = runCatching {
        val date = SimpleDateFormat(DateTimeFormat.ISO_LOCAL_DATE_TIME.code, LOCALE).format(milliseconds)
        LocalDateTime.parse(date)
    }.getOrNull()

    /**
     * The string must represent a valid date-time and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_LOCAL_DATE_TIME}.
     *
     * @param date  the text to parse such as "2007-12-03T10:15:30", not null
     * @return the parsed local date-time, not null
     */
    fun from(date: String) = runCatching {
        LocalDateTime.parse(date)
    }.getOrNull()

    /**
     * Obtains an instance of {@code LocalDateTime} from a text string in a specified format.
     *
     * @param date  the text to parse, not null
     * @param format  the format to use, not null
     * @return the parsed local date-time, not null
     */
    fun from(date: String, format: DateTimeFormat) = from(date, format.code)

    fun from(date: String, format: String) = runCatching {
        LocalDateTime.parse(date, format.toDateTimeFormat(LOCALE))
    }.getOrNull()
}