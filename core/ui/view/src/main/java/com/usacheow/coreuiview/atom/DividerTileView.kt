package com.usacheow.coreuiview.atom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DimenRes
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewDividerTileBinding
import com.usacheow.coreuiview.helper.MarginValues
import com.usacheow.coreuiview.helper.ThemeColorsAttrs
import com.usacheow.coreuiview.helper.dimen
import com.usacheow.coreuiview.helper.get
import com.usacheow.coreuiview.helper.resize
import com.usacheow.coreuiview.helper.updateMargins
import com.usacheow.coreuiview.R as CoreUiViewR
import com.usacheow.coreuitheme.R as CoreUiThemeR

class DividerTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs), Populatable<DividerTileItem> {

    private val binding by lazy { ViewDividerTileBinding.bind(this) }

    override fun populate(model: DividerTileItem) {
        binding.dividerBackgroundView.setBackgroundColor(model.color.get(context))
        binding.dividerBackgroundView.resize(
            widthPx = ViewGroup.LayoutParams.MATCH_PARENT,
            heightPx = dimen(model.heightResId).toInt()
        )
        binding.dividerBackgroundView.updateMargins(model.margin)
    }
}

data class DividerTileItem(
    val margin: MarginValues.Horizontal = MarginValues.Horizontal(0, 0),
    @DimenRes val heightResId: Int = CoreUiThemeR.dimen.divider_height_small,
    var color: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.outline),
) : ViewState(CoreUiViewR.layout.view_divider_tile) {

    companion object {

        fun getSmallDivider() = DividerTileItem()

        fun getMediumDivider() = DividerTileItem(heightResId = CoreUiThemeR.dimen.divider_height_medium)

        fun getLargeDivider() = DividerTileItem(heightResId = CoreUiThemeR.dimen.divider_height_large)
    }
}