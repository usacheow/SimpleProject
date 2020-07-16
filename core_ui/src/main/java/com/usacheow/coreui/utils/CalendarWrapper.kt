package com.usacheow.coreui.utils

import com.usacheow.coreui.utils.ext.LOCALE
import com.usacheow.coreui.utils.ext.parseTo
import com.usacheow.coreui.utils.ext.toCalendar
import com.usacheow.coreui.utils.ext.toDate
import java.text.DateFormatSymbols
import java.util.Calendar
import java.util.Date

private const val DAYS_OF_WEEK = 7
private const val OFFSET_FOR_SUNDAY = 1

class CalendarWrapper
private constructor(private val calendar: Calendar) {

    val date: Date get() = calendar.time
    val year: Int get() = calendar.get(Calendar.YEAR)
    val month: Int get() = calendar.get(Calendar.MONTH)
    val dayOfMonth: Int get() = calendar.get(Calendar.DAY_OF_MONTH)
    val dayOfWeek: Int get() = calendar.get(Calendar.DAY_OF_WEEK)

    val weekDaysShortNames: List<String>
        get() = DateFormatSymbols.getInstance(LOCALE())
            .shortWeekdays
            .drop(1)
            .toMutableList()
            .apply {
                add(first())
                removeAt(0)
            }
    val firstDayOffset: Int
        get() {
            var monthOffset = dayOfWeek.dec() - OFFSET_FOR_SUNDAY
            if (monthOffset < 0) {
                monthOffset += DAYS_OF_WEEK
            }
            return monthOffset
        }

    private val numberDayInMonth: Int get() = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    companion object {
        fun get(calendar: Calendar = Calendar.getInstance(LOCALE())) = CalendarWrapper(calendar)

        fun get(date: Date) = CalendarWrapper(date.toCalendar())

        fun get(unitTime: Long) = CalendarWrapper(unitTime.toCalendar())

        fun get(date: String, format: DateFormat) = CalendarWrapper(date.toDate(format).toCalendar())
    }

    fun turnOnNextMonth() {
        calendar.add(Calendar.MONTH, 1)
    }

    fun turnOnPrevMonth() {
        calendar.add(Calendar.MONTH, -1)
    }

    fun parseTo(format: DateFormat): String = calendar.time.parseTo(format)

    fun <T> getDaysListWithOffset(dayMapper: (Date) -> T): List<T?> {
        val calendar = Calendar.getInstance(LOCALE()).apply {
            set(year, month, 1)
        }

        val daysList = (1..numberDayInMonth).map {
            dayMapper(calendar.apply { set(Calendar.DATE, it) }.time)
        }

        return List(firstDayOffset) { null } + daysList
    }
}

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
    dd__MM__yyyy("dd.MM.yyyy"),
    dd_MMMM_yyyy("dd MMMM yyyy"),
    yyyy__MM__dd_hh_mm_ss("yyyy-MM-dd HH:mm:ss")
}