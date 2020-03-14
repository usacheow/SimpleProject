package com.kapmayn.coreuikit.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.chip.Chip
import com.kapmayn.coreuikit.R
import com.kapmayn.coreuikit.utils.ext.setVisible
import kotlinx.android.synthetic.main.view_chips_layout.view.chipsButton
import kotlinx.android.synthetic.main.view_chips_layout.view.chipsGroup

private const val CHIPS_WITHOUT_BUTTON_COUNT = 3

class ChipsLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_chips_layout, this)
    }

    fun populate(filters: Set<Filter>, filterClick: (Filter, Boolean) -> Unit) {
        chipsButton.setVisible(filters.size > CHIPS_WITHOUT_BUTTON_COUNT)
        chipsGroup.removeAllViews()
        filters.map { createChip(it, filterClick) }
            .forEach { chipsGroup.addView(it) }
    }

    private fun createChip(filter: Filter, filterClick: (Filter, Boolean) -> Unit): Chip {
        return (LayoutInflater.from(context).inflate(R.layout.view_chip_item, null) as Chip).apply {
            text = filter.name
            isChecked = filter.isChoose
            setOnCheckedChangeListener { chipView, isChecked ->
                filterClick(filter, isChecked)
            }
        }
    }
}

data class Filter(
    val id: Int,
    val name: String,
    var isChoose: Boolean = false
)