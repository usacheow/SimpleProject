package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarDayItemBinding
import com.usacheow.coreui.utils.view.color

class CalendarDayItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<CalendarDayItem> {

    private val binding by lazy { ViewCalendarDayItemBinding.bind(this) }

    override fun populate(model: CalendarDayItem) {
        binding.calendarDayNumberView.text = model.value
        val textColorId = when (model.isSelected) {
            true -> R.color.colorAccent
            false -> R.color.colorText
        }
        binding.calendarDayNumberView.typeface = when (model.isSelected) {
            true -> Typeface.DEFAULT_BOLD
            false -> Typeface.DEFAULT
        }
        binding.calendarDayNumberView.setTextColor(color(textColorId))
        model.indicatorColorId?.let {
            binding.calendarDayItemIndicatorView.setCardBackgroundColor(color(it))
        }
    }
}

data class CalendarDayItem(
    val value: String,
    val isSelected: Boolean = false,
    @ColorRes val indicatorColorId: Int? = null,
    val clickAction: () -> Unit = {}
) : ViewType(R.layout.view_calendar_day_item)