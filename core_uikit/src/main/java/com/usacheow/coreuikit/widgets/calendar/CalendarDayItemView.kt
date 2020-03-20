package com.usacheow.coreuikit.widgets.calendar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.color
import kotlinx.android.synthetic.main.view_calendar_day_item.view.calendarDayItemIndicatorView
import kotlinx.android.synthetic.main.view_calendar_day_item.view.calendarDayNumberView

class CalendarDayItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<CalendarDayItem> {

    override fun populate(model: CalendarDayItem) {
        calendarDayNumberView.text = model.value
        val textColorId = when (model.isSelected) {
            true -> R.color.colorAccent
            false -> R.color.colorText
        }
        calendarDayNumberView.typeface = when (model.isSelected) {
            true -> Typeface.DEFAULT_BOLD
            false -> Typeface.DEFAULT
        }
        calendarDayNumberView.setTextColor(color(textColorId))
        model.indicatorColorId?.let {
            calendarDayItemIndicatorView.setCardBackgroundColor(color(it))
        }
    }
}

data class CalendarDayItem(
    val value: String,
    val isSelected: Boolean = false,
    @ColorRes val indicatorColorId: Int? = null,
    val clickAction: () -> Unit = {}
) : ViewType(R.layout.view_calendar_day_item)