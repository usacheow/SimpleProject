package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.usacheow.corecommon.resource.ColorSource
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewBadgeTileBinding
import com.usacheow.coreuiview.helper.ThemeColorsAttrs
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.getColorInt
import com.usacheow.coreuiview.helper.ifFalse
import com.usacheow.coreuiview.helper.populate
import com.usacheow.coreuiview.helper.resize
import com.usacheow.coreuiview.R as CoreUiViewR

class BadgeTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialCardView(context, attrs), Populatable<BadgeTileItem> {

    private val binding by lazy { ViewBadgeTileBinding.bind(this) }

    override fun populate(model: BadgeTileItem) {
        val containerWidth = when (model.needAdaptWidth) {
            true -> ViewGroup.LayoutParams.WRAP_CONTENT
            false -> ViewGroup.LayoutParams.MATCH_PARENT
        }
        binding.root.resize(widthPx = containerWidth, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.clickableView.resize(widthPx = containerWidth, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.root.setCardBackgroundColor(model.backgroundColor.getColorInt(context))
        binding.clickableView.doOnClick(model.clickListener.ifFalse(model.isShimmer))

        binding.headerView.apply {
            populate(model.header)
            setTextColor(model.textColor.getColorInt(context))
        }
        binding.valueView.apply {
            populate(model.value)
            setTextColor(model.textColor.getColorInt(context))
        }
    }
}

data class BadgeTileItem(
    val header: TextSource? = null,
    val value: TextSource,
    val needAdaptWidth: Boolean = true,
    val textColor: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.onSurface),
    val backgroundColor: ColorSource = ColorSource.fromAttr(ThemeColorsAttrs.surface),
    val clickListener: (() -> Unit)? = null,
) : ViewState(CoreUiViewR.layout.view_badge_tile) {

    companion object {
        fun shimmer() = BadgeShimmerItem()
    }
}

class BadgeShimmerItem : ViewState(CoreUiViewR.layout.view_badge_shimmer)