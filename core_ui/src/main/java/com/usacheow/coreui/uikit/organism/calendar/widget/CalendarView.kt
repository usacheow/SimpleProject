package com.usacheow.coreui.uikit.organism.calendar.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarBinding
import com.usacheow.coreui.uikit.organism.calendar.CalendarGenerator
import com.usacheow.coreui.utils.values.*
import com.usacheow.coreui.utils.view.doOnClick
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class CalendarView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarItem> {

    private val binding by lazy { ViewCalendarBinding.bind(this) }

    private val generator = CalendarGenerator()
    private var model = CalendarItem(LocalDate.now())

    override fun onFinishInflate() {
        super.onFinishInflate()

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

        updateCalendar()
    }

    private fun updateCalendar() {
        binding.calendarMonthNameView.text = model.selectedMonth.month.getDisplayName(TextStyle.FULL, LOCALE())

        val monthDaysItem = generator.generateMonth(model.selectedMonth)
        monthDaysItem.days.forEach {
            it.indicatorColorId = model.selectedDaysWithColors[it.value]
        }

        binding.calendarMonthView.root.populate(monthDaysItem)
    }
}

data class CalendarItem(
    var selectedMonth: LocalDate,
    val selectedDaysWithColors: HashMap<LocalDate, Int> = hashMapOf(),
    val isScrollable: Boolean = false,
) : ViewType(R.layout.view_calendar)