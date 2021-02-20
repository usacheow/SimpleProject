package com.usacheow.coreui.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewDividerItemBinding
import com.usacheow.coreui.utils.MarginHorizontal
import com.usacheow.coreui.utils.updateMargins
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.resize

class DividerItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Populatable<DividerItem> {

    private val binding by lazy { ViewDividerItemBinding.bind(this) }

    override fun populate(model: DividerItem) {
        binding.dividerBackgroundView.setBackgroundColor(color(model.colorResId))
        binding.dividerBackgroundView.resize(
            widthPx = ViewGroup.LayoutParams.MATCH_PARENT,
            heightPx = resources.getDimension(model.heightResId).toInt()
        )
        binding.dividerBackgroundView.updateMargins(model.margin)
    }
}

data class DividerItem(
    val margin: MarginHorizontal = MarginHorizontal(0, 0),
    @DimenRes val heightResId: Int = R.dimen.divider_height_small,
    @ColorRes var colorResId: Int = R.color.colorDivider
) : ViewType(R.layout.view_divider_item) {

    companion object {

        fun getSmallDivider() = DividerItem()

        fun getBigDivider() = DividerItem(heightResId = R.dimen.divider_height_big)

    }
}