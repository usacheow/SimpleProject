package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarMonthNameItemBinding

class CalendarMonthNameItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarMonthNameItem> {

    private val binding by lazy { ViewCalendarMonthNameItemBinding.bind(this) }

    override fun populate(model: CalendarMonthNameItem) {
        binding.calendarMonthView.text = model.value
    }
}

data class CalendarMonthNameItem(
    val value: String
) : ViewType(R.layout.view_calendar_month_name_item)