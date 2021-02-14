package com.usacheow.coreui.utils.date

import com.usacheow.coreui.utils.values.LOCALE
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object LocalDateTimeFactory {

    /**
     * The string must represent a valid date-time and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_LOCAL_DATE_TIME}.
     *
     * @param date  the text to parse such as "2007-12-03T10:15:30", not null
     * @return the parsed local date-time, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    fun from(date: String) = LocalDateTime.parse(date)

    /**
     * Obtains an instance of {@code LocalDateTime} from a text string in a specified format.
     *
     * @param date  the text to parse, not null
     * @param format  the format to use, not null
     * @return the parsed local date-time, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    fun from(date: String, format: DateFormat) = from(date, format.code)

    fun from(date: String, format: String): LocalDateTime {
        val dateFormatter = DateTimeFormatter.ofPattern(format, LOCALE())
        return LocalDateTime.parse(date, dateFormatter)
    }
}