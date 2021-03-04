package com.usacheow.coreui.uikit.organism.calendar.list

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewDayItemBinding
import com.usacheow.coreui.utils.view.color
import java.time.LocalDate

class DayItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<DayItem> {

    private val binding by lazy { ViewDayItemBinding.bind(this) }

    override fun populate(model: DayItem) {
        binding.calendarDayNumberView.text = model.value.dayOfMonth.toString()

        binding.calendarDayNumberView.typeface = when (model.isSelected) {
            true -> Typeface.DEFAULT_BOLD
            false -> Typeface.DEFAULT
        }
        binding.calendarDayNumberView.setTextColor(color(when {
            !model.isActive -> R.color.disabled
            model.isSelected -> R.color.colorPrimary
            else -> R.color.text
        }))

        model.indicatorColorId?.let {
            binding.calendarDayItemIndicatorView.setCardBackgroundColor(color(it))
        }
    }
}

data class DayItem(
    val value: LocalDate,
    val isActive: Boolean = true,
    val isSelected: Boolean = false,
    @ColorRes val indicatorColorId: Int? = null,
    val clickAction: () -> Unit = {},
) : ViewType(R.layout.view_day_item)