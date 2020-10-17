package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import kotlinx.android.synthetic.main.view_calendar_month_item.view.calendarMonthDaysListView
import kotlinx.android.synthetic.main.view_calendar_month_item.view.calendarMonthNameView
import kotlin.math.min

private const val DAYS_OF_WEEK = 7

class CalendarMonthItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<CalendarMonthItem> {

    private val daysAdapter = ViewTypesAdapter()

    override fun onFinishInflate() {
        super.onFinishInflate()

        with(calendarMonthDaysListView) {
            layoutManager = GridLayoutManager(context, DAYS_OF_WEEK)
            adapter = daysAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun populate(model: CalendarMonthItem) {
        calendarMonthNameView.text = model.name
        daysAdapter.update(model.days)
    }
}

data class CalendarMonthItem(
    val name: String,
    val days: List<CalendarDayItem>
) : ViewType(R.layout.view_calendar_month_item)