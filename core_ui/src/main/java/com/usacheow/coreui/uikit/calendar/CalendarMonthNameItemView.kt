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
import com.usacheow.coreui.databinding.ViewCalendarMonthNameItemBinding
import com.usacheow.coreui.utils.view.color
import java.time.LocalDate

class CalendarMonthNameItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<CalendarMonthNameItem> {

    private val binding by lazy { ViewCalendarMonthNameItemBinding.bind(this) }

    override fun populate(model: CalendarMonthNameItem) {
        binding.calendarMonthNameView.text = model.name
    }
}

data class CalendarMonthNameItem(
    val name: String,
) : ViewType(R.layout.view_calendar_month_name_item)