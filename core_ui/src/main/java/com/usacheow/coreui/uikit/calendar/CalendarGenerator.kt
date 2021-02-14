package com.usacheow.coreui.uikit.calendar

import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.uikit.calendar.CalendarDayItem
import com.usacheow.coreui.uikit.calendar.CalendarEmptyDayItem
import com.usacheow.coreui.uikit.calendar.CalendarMonthNameItem
import com.usacheow.coreui.utils.values.LOCALE
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle

class CalendarGenerator {

    val weekDaysShortNames: List<String>
        get() = DayOfWeek
            .values()
            .map { it.getDisplayName(TextStyle.SHORT, LOCALE()) }

    fun generateMonth(date: LocalDate): List<ViewType> {
        val currentMonth = date.month
        var currentDay = date
        while (currentDay.dayOfMonth != 1) {
            currentDay = currentDay.minusDays(1)
        }

        val days = getEmptyDayItems(currentDay.dayOfWeek)

        while (currentDay.month == currentMonth) {
            days += getDayItem(currentDay, true)

            currentDay = currentDay.plusDays(1)
        }

        return days
    }

    fun generatePeriod(start: LocalDate, end: LocalDate): List<ViewType> {
        if (start >= end) {
            return emptyList()
        }

        return generatePreviousPart(start.minusDays(1)) + generateNextPart(start, end)
    }

    private fun generatePreviousPart(start: LocalDate): List<ViewType> {
        var currentDay = start
        val currentMonth = start.month
        var currentDayOfWeek = start.dayOfWeek

        val days = mutableListOf<ViewType>()

        while (currentDay.month == currentMonth) {
            days.add(0, getDayItem(currentDay, false))
            currentDayOfWeek = currentDay.dayOfWeek

            currentDay = currentDay.minusDays(1)
        }

        return mutableListOf(getMonthNameItem(currentMonth)) + getEmptyDayItems(currentDayOfWeek) + days
    }

    private fun generateNextPart(start: LocalDate, end: LocalDate): List<ViewType> {
        var currentDay = start
        var currentMonth = start.month

        val days = mutableListOf<ViewType>()

        while (currentDay <= end) {
            if (currentDay.month != currentMonth) {
                currentMonth = currentDay.month
                days += getMonthNameItem(currentMonth)
                days += getEmptyDayItems(currentDay.dayOfWeek)
            }
            days += getDayItem(currentDay, true)

            currentDay = currentDay.plusDays(1)
        }

        return days
    }

    private fun getMonthNameItem(month: Month): ViewType = CalendarMonthNameItem(month.getDisplayName(TextStyle.FULL, LOCALE()))

    private fun getDayItem(day: LocalDate, isActive: Boolean): ViewType = CalendarDayItem(day, isActive = isActive)

    private fun getEmptyDayItems(dayOfWeek: DayOfWeek): MutableList<ViewType> = MutableList(dayOfWeek.value - 1) { CalendarEmptyDayItem }
}