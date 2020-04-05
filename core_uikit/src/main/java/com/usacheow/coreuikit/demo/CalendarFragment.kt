package com.usacheow.coreuikit.demo

import android.os.Bundle
import android.view.WindowInsets
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.adapters.ViewTypesAdapter
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.CalendarWrapper
import com.usacheow.coreuikit.utils.DateFormat
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.isToday
import com.usacheow.coreuikit.widgets.calendar.CalendarDayItem
import com.usacheow.coreuikit.widgets.calendar.CalendarMonthItem
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_calendar.calendarListView
import java.util.Date

class CalendarFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_calendar

    private var currentMonth = CalendarWrapper.getCurrentDayCalendar()

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun onApplyWindowInsets(insets: WindowInsets, padding: PaddingValue) {
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
                value = it.dayOfMonth.toString(),
                isSelected = it.date.isToday(),
                indicatorColorId = selectedDays[it.date],
                clickAction = { clickAction(it.date) }
            )
        }.map { it ?: CalendarDayItem("") }
    }
}