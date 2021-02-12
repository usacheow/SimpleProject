package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarMonthItemBinding

private const val DAYS_OF_WEEK = 7

class CalendarMonthItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<CalendarMonthItem> {

    private val daysAdapter = ViewTypesAdapter()

    private val binding by lazy { ViewCalendarMonthItemBinding.bind(this) }

    override fun onFinishInflate() {
        super.onFinishInflate()

        with(binding.calendarMonthDaysListView) {
            layoutManager = GridLayoutManager(context, DAYS_OF_WEEK)
            adapter = daysAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun populate(model: CalendarMonthItem) {
        binding.calendarMonthNameView.text = model.name
        daysAdapter.update(model.days)
    }
}

data class CalendarMonthItem(
    val name: String,
    val days: List<CalendarDayItem>
) : ViewType(R.layout.view_calendar_month_item)