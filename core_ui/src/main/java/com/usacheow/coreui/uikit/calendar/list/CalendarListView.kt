package com.usacheow.coreui.uikit.calendar.list

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewCalendarBinding
import com.usacheow.coreui.uikit.calendar.CalendarGenerator
import com.usacheow.coreui.uikit.calendar.MonthNameItem
import com.usacheow.coreui.utils.date.DateFormat
import com.usacheow.coreui.utils.values.*
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.coreui.utils.view.updateMargins
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class CalendarListView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), Populatable<CalendarListItem> {

    private val viewTypesAdapter = ViewTypesAdapter()

    init {
        adapter = viewTypesAdapter
        layoutManager = GridLayoutManager(context, DAYS_OF_WEEK).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = when {
                    viewTypesAdapter.entities[position] is MonthNameItem -> DAYS_OF_WEEK

                    else -> 1
                }
            }
        }
        post {
            updatePadding(
                left = resources.getDimension(R.dimen.left_right_margin).toInt(),
                right = resources.getDimension(R.dimen.left_right_margin).toInt(),
            )
        }
    }

    override fun populate(model: CalendarListItem) {
        viewTypesAdapter.update(CalendarGenerator().generatePeriod(
            model.start,
            model.end,
        ))
    }
}

data class CalendarListItem(
    var start: LocalDate,
    var end: LocalDate,
)