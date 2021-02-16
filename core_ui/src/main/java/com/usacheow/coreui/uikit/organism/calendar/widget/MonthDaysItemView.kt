package com.usacheow.coreui.uikit.organism.calendar.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewDayItemBinding
import com.usacheow.coreui.databinding.ViewMonthItemBinding
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible
import java.time.LocalDate

class MonthDaysItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), Populatable<MonthDaysItem> {

    private val binding by lazy { ViewMonthItemBinding.bind(this) }

    override fun populate(model: MonthDaysItem) {
        getEmptyDays().map { it.makeGone() }
        getMonthDays().map { it.root.makeGone() }

        val emptyDaysArray = getEmptyDays()
            .take(model.emptyDaysCount)
            .map { it.apply { makeVisible() } }

        val dayArray = getMonthDays()
            .take(model.daysCount)
            .mapIndexed { index, binding -> binding.apply { populate(model.days[index]) } }
            .map { it.root.apply { makeVisible() } }

        binding.calendarMonthDayFlow.referencedIds = (emptyDaysArray + dayArray).map { it.id }.toIntArray()
    }

    private fun ViewDayItemBinding.populate(model: MonthDayItem) {
        calendarDayNumberView.text = model.value.dayOfMonth.toString()

        calendarDayNumberView.typeface = when (model.isSelected) {
            true -> Typeface.DEFAULT_BOLD
            false -> Typeface.DEFAULT
        }
        calendarDayNumberView.setTextColor(color(when {
            !model.isActive -> R.color.disabled
            model.isSelected -> R.color.colorAccent
            else -> R.color.colorText
        }))

        model.indicatorColorId?.let {
            calendarDayItemIndicatorView.setCardBackgroundColor(color(it))
        }
    }

    private fun getEmptyDays() = listOf(
        binding.monthEmptyDay1View,
        binding.monthEmptyDay2View,
        binding.monthEmptyDay3View,
        binding.monthEmptyDay4View,
        binding.monthEmptyDay5View,
        binding.monthEmptyDay6View,
    )

    private fun getMonthDays() = listOf(
        binding.monthDay1View,
        binding.monthDay2View,
        binding.monthDay3View,
        binding.monthDay4View,
        binding.monthDay5View,
        binding.monthDay6View,
        binding.monthDay7View,
        binding.monthDay8View,
        binding.monthDay9View,
        binding.monthDay10View,
        binding.monthDay11View,
        binding.monthDay12View,
        binding.monthDay13View,
        binding.monthDay14View,
        binding.monthDay15View,
        binding.monthDay16View,
        binding.monthDay17View,
        binding.monthDay18View,
        binding.monthDay19View,
        binding.monthDay20View,
        binding.monthDay21View,
        binding.monthDay22View,
        binding.monthDay23View,
        binding.monthDay24View,
        binding.monthDay25View,
        binding.monthDay26View,
        binding.monthDay27View,
        binding.monthDay28View,
        binding.monthDay29View,
        binding.monthDay30View,
        binding.monthDay31View,
    )
}

data class MonthDaysItem(
    val offset: Int,
    val days: List<MonthDayItem>,
) : ViewType(R.layout.view_month_item) {

    val emptyDaysCount get() = (offset - 1) % 7

    val daysCount get() = days.size
}

data class MonthDayItem(
    val value: LocalDate,
    val isActive: Boolean = true,
    val isSelected: Boolean = false,
    @ColorRes var indicatorColorId: Int? = null,
)