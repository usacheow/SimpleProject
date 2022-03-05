package com.usacheow.coreuiview.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DimenRes
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.databinding.ViewDividerTileBinding
import com.usacheow.coreuiview.tools.MarginValues
import com.usacheow.coreuiview.tools.resource.ThemeColorsAttrs
import com.usacheow.coreuiview.tools.applyMargins
import com.usacheow.coreuiview.tools.resource.dimen
import com.usacheow.coreuiview.tools.resize
import com.usacheow.coreuiview.tools.resource.get
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
        binding.dividerBackgroundView.applyMargins(model.margin)
    }
}

data class DividerTileItem(
    val margin: MarginValues = MarginValues(start = 0, end = 0),
    @DimenRes val heightResId: Int = CoreUiThemeR.dimen.divider_height_small,
    var color: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.outline),
) : ViewState(CoreUiViewR.layout.view_divider_tile) {

    companion object {

        fun getSmallDivider() = DividerTileItem()

        fun getMediumDivider() = DividerTileItem(heightResId = CoreUiThemeR.dimen.divider_height_medium)

        fun getLargeDivider() = DividerTileItem(heightResId = CoreUiThemeR.dimen.divider_height_large)
    }
}