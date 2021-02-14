package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarWeekDayNameItemBinding

class CalendarWeekDayNameItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarWeekDayNameItem> {

    private val binding by lazy { ViewCalendarWeekDayNameItemBinding.bind(this) }

    override fun populate(model: CalendarWeekDayNameItem) {
        binding.calendarMonthView.text = model.value
    }
}

data class CalendarWeekDayNameItem(
    val value: String
) : ViewType(R.layout.view_calendar_week_day_name_item)