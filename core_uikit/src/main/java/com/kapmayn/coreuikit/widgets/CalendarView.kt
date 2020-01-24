package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.kapmayn.core.utils.CalendarWrapper
import com.kapmayn.core.utils.DateFormat
import com.kapmayn.core.utils.isToday
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.adapters.ViewTypesAdapter
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import com.kapmayn.coreuikit.utils.doWithAutoTransition
import kotlinx.android.synthetic.main.view_calendar.view.calendarDaysListView
import kotlinx.android.synthetic.main.view_calendar.view.calendarItemRootView
import kotlinx.android.synthetic.main.view_calendar.view.calendarMonthView
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
    private var currentMonth = CalendarWrapper.getCurrentDayCalendar()
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

        calendarPrevButton.setOnClickListener {
            currentMonth.turnOnPrevMonth()
            updateCalendar()
        }
        calendarNextButton.setOnClickListener {
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
        calendarItemRootView.doWithAutoTransition {
            calendarMonthView.text = currentMonth.parseTo(DateFormat.MMMM__yy)
            daysAdapter.update(getDaysList(selectedDays, clickAction))
        }
    }

    private fun getWeekDaysShortNames() = currentMonth.weekDaysShortNames
        .map { CalendarMonthItem(it) }

    private fun getDaysList(
        selectedDays: HashMap<Date, Int>,
        clickAction: (Date) -> Unit
    ): List<CalendarDayItem> {

        return currentMonth.getDaysListWithOffset {
            CalendarDayItem(
                value = it.dayInMonth.toString(),
                isSelected = it.date.isToday(),
                indicatorColorId = selectedDays[it.date],
                clickAction = { clickAction(it.date) }
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