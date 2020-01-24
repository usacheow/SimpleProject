package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.view_calendar_month_item.view.calendarMonthView

class CalendarMonthItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<CalendarMonthItem> {

    override fun populate(model: CalendarMonthItem) {
        calendarMonthView.text = model.value
    }
}

data class CalendarMonthItem(
    val value: String
) : ViewType(R.layout.view_calendar_month_item)