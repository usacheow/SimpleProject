package com.usacheow.coreui.uikit.calendar.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.coreui.databinding.ViewWeekItemBinding
import com.usacheow.coreui.utils.values.LOCALE
import java.time.DayOfWeek
import java.time.format.TextStyle

class WeekItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy { ViewWeekItemBinding.bind(this) }

    init {
        post {
            val weekDaysShortNames = DayOfWeek
                .values()
                .map { it.getDisplayName(TextStyle.SHORT, LOCALE()) }

            listOf(
                binding.weekDay1View,
                binding.weekDay2View,
                binding.weekDay3View,
                binding.weekDay4View,
                binding.weekDay5View,
                binding.weekDay6View,
                binding.weekDay7View,
            ).forEachIndexed { index, textView -> textView.text = weekDaysShortNames[index] }
        }
    }
}