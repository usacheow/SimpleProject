package com.kapmayn.core.utils

import java.text.DateFormatSymbols
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val DAYS_OF_WEEK = 7
private const val OFFSET_FOR_SUNDAY = 1

class CalendarWrapper
private constructor(
    private val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
) {

    val date: Date
        get() = calendar.time

    val year: Int
        get() = calendar.getActualMaximum(Calendar.YEAR)

    val month: Int
        get() = calendar.getActualMaximum(Calendar.MONTH)

    val dayInMonth: Int
        get() = calendar.get(Calendar.DAY_OF_MONTH)

    val dayOfWeek: Int
        get() = calendar.get(Calendar.DAY_OF_WEEK)

    val numberDayInMonth: Int
        get() = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val weekDaysShortNames: List<String>
        get() {

            return DateFormatSymbols.getInstance(Locale.getDefault())
                .shortWeekdays
                .drop(1)
                .toMutableList()
                .apply {
                    add(first())
                    removeAt(0)
                }
        }

    val firstDayOffset: Int
        get() {
            var monthOffset = calendar.get(Calendar.DAY_OF_WEEK).dec() - OFFSET_FOR_SUNDAY
            if (monthOffset < 0) {
                monthOffset += DAYS_OF_WEEK
            }
            return monthOffset
        }

    companion object {

        fun getCurrentDayCalendar() = CalendarWrapper()

        fun get(calendar: Calendar) = CalendarWrapper(calendar)

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

    fun copy(
        year: Int = calendar.get(Calendar.YEAR),
        month: Int = calendar.get(Calendar.MONTH),
        day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    ): CalendarWrapper {
        return get(Calendar.getInstance(Locale.getDefault()).apply {
            set(year, month, day)
        })
    }

    fun <T> getDaysListWithOffset(dayMapper: (CalendarWrapper) -> T): List<T?> {
        val daysList = List(numberDayInMonth) { it + 1 }
            .map { copy(day = it) }
            .map { dayMapper(it) }

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