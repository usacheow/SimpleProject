package com.usacheow.coreui.utils.date

import com.usacheow.coreui.utils.values.LOCALE
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object LocalTimeFactory {

    /**
     * The string must represent a valid time and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_LOCAL_TIME}.
     *
     * @param date  the text to parse such as "10:15:30", not null
     * @return the parsed local time, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    fun from(date: String) = LocalTime.parse(date)

    /**
     * Obtains an instance of {@code LocalTime} from a text string in a specified format.
     *
     * @param date  the text to parse, not null
     * @param format  the format to use, not null
     * @return the parsed local time, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    fun from(date: String, format: DateFormat) = from(date, format.code)

    fun from(date: String, format: String): LocalTime {
        val dateFormatter = DateTimeFormatter.ofPattern(format, LOCALE())
        return LocalTime.parse(date, dateFormatter)
    }
}