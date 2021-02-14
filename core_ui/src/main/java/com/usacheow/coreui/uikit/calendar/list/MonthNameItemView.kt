package com.usacheow.coreui.uikit.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewMonthNameItemBinding

class MonthNameItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<MonthNameItem> {

    private val binding by lazy { ViewMonthNameItemBinding.bind(this) }

    override fun populate(model: MonthNameItem) {
        binding.calendarMonthNameView.text = model.name
    }
}

data class MonthNameItem(
    val name: String,
) : ViewType(R.layout.view_month_name_item)