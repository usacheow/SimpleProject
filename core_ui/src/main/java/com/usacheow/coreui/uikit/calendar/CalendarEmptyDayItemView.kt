package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Space
import androidx.annotation.ColorRes
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarDayItemBinding
import com.usacheow.coreui.utils.view.color
import java.time.LocalDate

class CalendarEmptyDayItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Populatable<CalendarEmptyDayItem> {

    override fun populate(model: CalendarEmptyDayItem) {}
}

object CalendarEmptyDayItem : ViewType(R.layout.view_calendar_empty_day_item)