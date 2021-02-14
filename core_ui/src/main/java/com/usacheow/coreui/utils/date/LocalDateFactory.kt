package com.usacheow.coreui.utils.date

import com.usacheow.coreui.utils.values.LOCALE
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

object LocalDateFactory {

    /**
     * The string must represent a valid date and is parsed using
     * {@link java.time.format.DateTimeFormatter#ISO_LOCAL_DATE}.
     *
     * @param date  the text to parse such as "2007-12-03", not null
     * @return the parsed local date, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    fun from(date: String) = LocalDate.parse(date)

    /**
     * Obtains an instance of {@code LocalDate} from a text string in a specified format.
     *
     * @param date the text to parse, not null
     * @param format  the format of date, not null
     * @return the parsed local date, not null
     * @throws DateTimeParseException if the text cannot be parsed
     */
    fun from(date: String, format: DateFormat) = from(date, format.code)

    fun from(date: String, format: String): LocalDate {
        val dateFormatter = DateTimeFormatter.ofPattern(format, LOCALE())
        return LocalDate.parse(date, dateFormatter)
    }
}