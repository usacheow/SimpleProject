package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.calendar.widget.CalendarItem
import com.usacheow.coreui.utils.values.TODAY
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.demo.databinding.FragmentWidgetsBinding

class CalendarWidgetFragment : SimpleFragment<FragmentWidgetsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentWidgetsBinding::inflate,
    )

    companion object {
        fun newInstance() = CalendarWidgetFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Calendar widget"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            CalendarItem(selectedMonth = TODAY(), isScrollable = true),
        ))
    }
}