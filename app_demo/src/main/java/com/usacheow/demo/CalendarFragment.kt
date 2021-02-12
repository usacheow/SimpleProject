package com.usacheow.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.calendar.CalendarDayItem
import com.usacheow.coreui.uikit.calendar.CalendarMonthItem
import com.usacheow.coreui.utils.values.CalendarWrapper
import com.usacheow.coreui.utils.values.DateFormat
import com.usacheow.coreui.utils.values.isToday
import com.usacheow.coreui.utils.values.parseTo
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.demo.databinding.FragmentCalendarBinding
import java.util.*

class CalendarFragment : SimpleFragment<FragmentCalendarBinding>() {

    private var currentMonth = CalendarWrapper.get()

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCalendarBinding {
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.calendarListView.updatePadding(
            top = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.calendarListView.layoutManager = LinearLayoutManager(context)
        binding.calendarListView.adapter = ViewTypesAdapter((0..11).map {
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