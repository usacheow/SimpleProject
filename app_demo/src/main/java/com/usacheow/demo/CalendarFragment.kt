package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.calendar.CalendarDayItem
import com.usacheow.coreui.uikit.calendar.CalendarMonthItem
import com.usacheow.coreui.utils.CalendarWrapper
import com.usacheow.coreui.utils.DateFormat
import com.usacheow.coreui.utils.ext.PaddingValue
import com.usacheow.coreui.utils.ext.isToday
import com.usacheow.coreui.utils.ext.parseTo
import kotlinx.android.synthetic.main.fragment_calendar.calendarListView
import java.util.Date

class CalendarFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_calendar

    private var currentMonth = CalendarWrapper.get()

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        calendarListView.updatePadding(
            top = insets.systemWindowInsetTop,
            bottom = insets.systemWindowInsetBottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        calendarListView.layoutManager = LinearLayoutManager(context)
        calendarListView.adapter = ViewTypesAdapter((0..11).map {
            currentMonth.getMonthItem().also { currentMonth.turnOnNextMonth() }
        })
    }

    private fun CalendarWrapper.getMonthItem(): CalendarMonthItem {

        return CalendarMonthItem(
            name = parseTo(DateFormat.MMMM__yy),
            days = getDaysList()
        )
    }

    private fun CalendarWrapper.getDaysList(
        selectedDays: HashMap<Date, Int> = hashMapOf(),
        clickAction: (Date) -> Unit = {}
    ): List<CalendarDayItem> {

        return getDaysListWithOffset {
            CalendarDayItem(
                value = it.parseTo(DateFormat.dd),
                isSelected = it.isToday(),
                indicatorColorId = selectedDays[it],
                clickAction = { clickAction(it) }
            )
        }.map { it ?: CalendarDayItem("") }
    }
}