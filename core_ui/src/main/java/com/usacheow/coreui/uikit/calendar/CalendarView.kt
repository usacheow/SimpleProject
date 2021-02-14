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
import com.usacheow.coreui.databinding.ViewCalendarBinding
import com.usacheow.coreui.utils.date.DateFormat
import com.usacheow.coreui.utils.values.*
import com.usacheow.coreui.utils.view.doOnClick
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

private const val DAYS_OF_WEEK = 7

class CalendarView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarItem> {

    private val binding by lazy { ViewCalendarBinding.bind(this) }

    private val daysAdapter = ViewTypesAdapter()
    private val weekDaysAdapter = ViewTypesAdapter()
    private val generator = CalendarGenerator()

    private var model = CalendarItem(LocalDate.now())

    override fun onFinishInflate() {
        super.onFinishInflate()

        with(binding.calendarWeekDaysListView) {
            layoutManager = GridLayoutManager(context, DAYS_OF_WEEK)
            adapter = weekDaysAdapter
            isNestedScrollingEnabled = false
        }

        with(binding.calendarDaysListView) {
            layoutManager = GridLayoutManager(context, DAYS_OF_WEEK)
            adapter = daysAdapter
            isNestedScrollingEnabled = false
        }

        binding.calendarPrevButton.doOnClick {
            model.selectedMonth = model.selectedMonth.minusMonths(1)
            updateCalendar()
        }
        binding.calendarNextButton.doOnClick {
            model.selectedMonth = model.selectedMonth.plusMonths(1)
            updateCalendar()
        }
    }

    override fun populate(model: CalendarItem) {
        this.model = model
        binding.calendarPrevButton.isVisible = model.isScrollable
        binding.calendarNextButton.isVisible = model.isScrollable

        updateWeekDayNames()
        updateCalendar()
    }

    private fun updateWeekDayNames() {
        val names = generator
            .weekDaysShortNames
            .map { CalendarWeekDayNameItem(it) }

        weekDaysAdapter.update(names)
    }

    private fun updateCalendar() {
        binding.calendarMonthNameView.text = model.selectedMonth.month.getDisplayName(TextStyle.FULL, LOCALE())

        val dayList = generator.generateMonth(model.selectedMonth).map {
            when (it) {
                is CalendarDayItem -> it.copy(
                    indicatorColorId = model.selectedDaysWithColors[it.value],
                    clickAction = { model.clickAction(it.value) },
                )

                else -> it
            }
        }
        daysAdapter.update(dayList)
    }
}

data class CalendarItem(
    var selectedMonth: LocalDate,
    val selectedDaysWithColors: HashMap<LocalDate, Int> = hashMapOf(),
    val isScrollable: Boolean = false,
    val clickAction: (LocalDate) -> Unit = {}
) : ViewType(R.layout.view_calendar)