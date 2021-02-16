package com.usacheow.coreui.uikit.organism.calendar

import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.uikit.organism.calendar.list.DayItem
import com.usacheow.coreui.uikit.organism.calendar.widget.MonthDayItem
import com.usacheow.coreui.uikit.organism.calendar.widget.MonthDaysItem
import com.usacheow.coreui.utils.values.LOCALE
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle

class CalendarGenerator {

    fun generateMonth(date: LocalDate): MonthDaysItem {
        val currentMonth = date.month
        var currentDay = date
        while (currentDay.dayOfMonth != 1) {
            currentDay = currentDay.minusDays(1)
        }

        val offset = currentDay.dayOfWeek.value

        val days = mutableListOf<MonthDayItem>()
        while (currentDay.month == currentMonth) {
            days += MonthDayItem(currentDay, isActive = true)

            currentDay = currentDay.plusDays(1)
        }

        return MonthDaysItem(
            offset = offset,
            days = days,
        )
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

        return mutableListOf(getMonthNameItem(currentMonth)) + days
    }

    private fun generateNextPart(start: LocalDate, end: LocalDate): List<ViewType> {
        var currentDay = start
        var currentMonth = start.month

        val days = mutableListOf<ViewType>()

        while (currentDay <= end) {
            if (currentDay.month != currentMonth) {
                currentMonth = currentDay.month
                days += getMonthNameItem(currentMonth)
            }
            days += getDayItem(currentDay, true)

            currentDay = currentDay.plusDays(1)
        }

        return days
    }

    private fun getMonthNameItem(month: Month): ViewType = MonthNameItem(month.getDisplayName(TextStyle.FULL, LOCALE()))

    private fun getDayItem(day: LocalDate, isActive: Boolean): ViewType = DayItem(day, isActive = isActive)
}