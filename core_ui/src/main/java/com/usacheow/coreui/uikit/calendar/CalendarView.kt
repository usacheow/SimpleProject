package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.values.CalendarWrapper
import com.usacheow.coreui.utils.values.DateFormat
import com.usacheow.coreui.utils.values.isToday
import com.usacheow.coreui.utils.values.parseTo
import com.usacheow.coreui.utils.view.doOnClick
import kotlinx.android.synthetic.main.view_calendar.view.calendarDaysListView
import kotlinx.android.synthetic.main.view_calendar.view.calendarMonthNameView
import kotlinx.android.synthetic.main.view_calendar.view.calendarNextButton
import kotlinx.android.synthetic.main.view_calendar.view.calendarPrevButton
import kotlinx.android.synthetic.main.view_calendar.view.calendarWeekDaysListView
import java.util.Date

private const val DAYS_OF_WEEK = 7

class CalendarView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarItem> {

    private val daysAdapter = ViewTypesAdapter()
    private val weekDaysAdapter = ViewTypesAdapter()
    private var currentMonth = CalendarWrapper.get()
    private var selectedDays: HashMap<Date, Int> = hashMapOf()
    private var clickAction: (Date) -> Unit = {}

    override fun onFinishInflate() {
        super.onFinishInflate()

        with(calendarWeekDaysListView) {
            layoutManager = GridLayoutManager(context, DAYS_OF_WEEK)
            adapter = weekDaysAdapter
            isNestedScrollingEnabled = false
        }

        with(calendarDaysListView) {
            layoutManager = GridLayoutManager(context, DAYS_OF_WEEK)
            adapter = daysAdapter
            isNestedScrollingEnabled = false
        }

        calendarPrevButton.doOnClick {
            currentMonth.turnOnPrevMonth()
            updateCalendar()
        }
        calendarNextButton.doOnClick {
            currentMonth.turnOnNextMonth()
            updateCalendar()
        }
    }

    override fun populate(model: CalendarItem) {
        currentMonth = CalendarWrapper.get(model.selectedMonth)
        selectedDays = model.selectedDays
        clickAction = model.clickAction
        calendarPrevButton.isVisible = model.isScrollable
        calendarNextButton.isVisible = model.isScrollable
        weekDaysAdapter.update(getWeekDaysShortNames())
        updateCalendar()
    }

    private fun updateCalendar() {
        calendarMonthNameView.text = currentMonth.parseTo(DateFormat.MMMM__yy)
        daysAdapter.update(getDaysList(selectedDays, clickAction))
    }

    private fun getWeekDaysShortNames() = currentMonth.weekDaysShortNames
        .map { CalendarMonthNameItem(it) }

    private fun getDaysList(
        selectedDays: HashMap<Date, Int>,
        clickAction: (Date) -> Unit
    ): List<CalendarDayItem> {

        return currentMonth.getDaysListWithOffset {
            CalendarDayItem(
                value = it.parseTo(DateFormat.dd),
                isSelected = it.isToday(),
                indicatorColorId = selectedDays[it],
                clickAction = { clickAction(it) }
            )
        }.map { it ?: CalendarDayItem("") }
    }
}

data class CalendarItem(
    val selectedMonth: Date,
    val selectedDays: HashMap<Date, Int> = hashMapOf(),
    val isScrollable: Boolean = false,
    val clickAction: (Date) -> Unit = {}
) : ViewType(R.layout.view_calendar)