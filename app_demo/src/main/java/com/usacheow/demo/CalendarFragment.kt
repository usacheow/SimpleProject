package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.calendar.CalendarGenerator
import com.usacheow.coreui.uikit.calendar.CalendarMonthNameItem
import com.usacheow.coreui.utils.values.DAYS_OF_WEEK
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.demo.databinding.FragmentCalendarBinding
import java.time.LocalDate

class CalendarFragment : SimpleFragment<FragmentCalendarBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentCalendarBinding::inflate,
    )

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.calendarListView.updatePadding(
            top = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        val adapter = ViewTypesAdapter()
        binding.calendarListView.adapter = adapter

        binding.calendarListView.layoutManager = GridLayoutManager(context, DAYS_OF_WEEK).apply {
            setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = when {
                    adapter.entities[position] is CalendarMonthNameItem -> DAYS_OF_WEEK

                    else -> 1
                }
            })
        }

        adapter.update(CalendarGenerator().generatePeriod(
            LocalDate.parse("2021-02-14"),
            LocalDate.parse("2022-02-14"),
        ))
    }
}