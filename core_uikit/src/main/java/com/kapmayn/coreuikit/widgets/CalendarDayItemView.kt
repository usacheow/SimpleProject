package com.kapmayn.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.base.Populatable
import com.kapmayn.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.view_calendar_day_item.view.calendarDayItemIndicatorView
import kotlinx.android.synthetic.main.view_calendar_day_item.view.calendarDayNumberView

class CalendarDayItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<CalendarDayItem> {

    override fun populate(model: CalendarDayItem) {
        calendarDayNumberView.text = model.value
        val textColor = when (model.isSelected) {
            true -> R.color.colorAccent
            false -> R.color.colorText
        }
        calendarDayNumberView.setTextColor(ContextCompat.getColor(context, textColor))
        model.indicatorColorId?.let {
            calendarDayItemIndicatorView.setCardBackgroundColor(ContextCompat.getColor(context, it))
        }
    }
}

data class CalendarDayItem(
    val value: String,
    val isSelected: Boolean = false,
    @ColorRes val indicatorColorId: Int? = null,
    val clickAction: () -> Unit = {}
) : ViewType(R.layout.view_calendar_day_item)