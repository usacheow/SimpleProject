package com.usacheow.corecommon.date

import com.usacheow.corecommon.strings.DEFAULT_LOCALE
import java.time.LocalTime

object LocalTimeFactory {

    /**
     * The string must represent a valid time and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_LOCAL_TIME}.
     *
     * @param date  the text to parse such as "10:15:30", not null
     * @return the parsed local time, not null
     */
    fun from(date: String) = runCatching {
        LocalTime.parse(date)
    }.getOrNull()

    /**
     * Obtains an instance of {@code LocalTime} from a text string in a specified format.
     *
     * @param date  the text to parse, not null
     * @param format  the format to use, not null
     * @return the parsed local time, not null
     */
    fun from(date: String, format: DateTimeFormat) = from(date, format.code)

    fun from(date: String, format: String) = runCatching {
        LocalTime.parse(date, format.toDateTimeFormat(DEFAULT_LOCALE))
    }.getOrNull()
}