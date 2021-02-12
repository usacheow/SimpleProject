package com.usacheow.coreui.uikit.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.material.chip.Chip
import com.usacheow.coreui.R
import com.usacheow.coreui.databinding.ViewChipsLayoutBinding
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.setVisible

private const val CHIPS_WITHOUT_BUTTON_COUNT = 3

class ChipsLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    var onAllChipsClicked: (() -> Unit)? = null

    private val binding by lazy { ViewChipsLayoutBinding.inflate(LayoutInflater.from(context), this, true) }

    fun populate(filters: Set<Filter>, filterClick: (Filter, Boolean) -> Unit) {
        binding.chipsButton.setVisible(filters.size > CHIPS_WITHOUT_BUTTON_COUNT)
        binding.chipsButton.doOnClick { onAllChipsClicked?.invoke() }
        binding.chipsGroup.removeAllViews()
        filters.map { createChip(it, filterClick) }
            .forEach { binding.chipsGroup.addView(it) }
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