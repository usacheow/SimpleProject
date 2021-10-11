package com.usacheow.coreui.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewDividerTileBinding
import com.usacheow.coreui.utils.MarginHorizontal
import com.usacheow.coreui.utils.updateMargins
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.dimen
import com.usacheow.coreui.utils.view.resize

class DividerTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), Populatable<DividerTileItem> {

    private val binding by lazy { ViewDividerTileBinding.bind(this) }

    override fun populate(model: DividerTileItem) {
        binding.dividerBackgroundView.setBackgroundColor(color(model.colorResId))
        binding.dividerBackgroundView.resize(
            widthPx = ViewGroup.LayoutParams.MATCH_PARENT,
            heightPx = dimen(model.heightResId).toInt()
        )
        binding.dividerBackgroundView.updateMargins(model.margin)
    }
}

data class DividerTileItem(
    val margin: MarginHorizontal = MarginHorizontal(0, 0),
    @DimenRes val heightResId: Int = CoreUiR.dimen.divider_height_small,
    @ColorRes var colorResId: Int = CoreUiR.color.divider,
) : ViewState(CoreUiR.layout.view_divider_tile) {

    companion object {

        fun getSmallDivider() = DividerTileItem()

        fun getMediumDivider() = DividerTileItem(heightResId = CoreUiR.dimen.divider_height_medium)

        fun getLargeDivider() = DividerTileItem(heightResId = CoreUiR.dimen.divider_height_large)
    }
}