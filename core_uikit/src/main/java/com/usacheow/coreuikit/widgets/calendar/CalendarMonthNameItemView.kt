package com.usacheow.coreuikit.widgets.calendar

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.view_calendar_month_name_item.view.calendarMonthView

class CalendarMonthNameItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarMonthNameItem> {

    override fun populate(model: CalendarMonthNameItem) {
        calendarMonthView.text = model.value
    }
}

data class CalendarMonthNameItem(
    val value: String
) : ViewType(R.layout.view_calendar_month_name_item)