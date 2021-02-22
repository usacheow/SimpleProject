package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.organism.calendar.list.CalendarListItem
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.appdemo.databinding.FragmentCalendarListBinding
import java.time.LocalDate

class CalendarListFragment : SimpleFragment<FragmentCalendarListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentCalendarListBinding::inflate,
    )

    companion object {
        fun newInstance() = CalendarListFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.calendarListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.root.populate(CalendarListItem(
            start = LocalDate.parse("2021-02-14"),
            end = LocalDate.parse("2022-02-14"),
        ))
    }
}