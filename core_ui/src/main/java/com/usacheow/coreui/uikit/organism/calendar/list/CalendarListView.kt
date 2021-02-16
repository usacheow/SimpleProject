package com.usacheow.coreui.uikit.organism.calendar.list

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.uikit.organism.calendar.CalendarGenerator
import com.usacheow.coreui.uikit.organism.calendar.MonthNameItem
import com.usacheow.coreui.utils.values.*
import com.usacheow.coreui.utils.view.dimen
import java.time.LocalDate

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
                left = dimen(R.dimen.left_right_margin).toInt(),
                right = dimen(R.dimen.left_right_margin).toInt(),
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