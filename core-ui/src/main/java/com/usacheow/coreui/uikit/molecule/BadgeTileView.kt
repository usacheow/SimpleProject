package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView
import com.usacheow.core.resource.ColorSource
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewBadgeTileBinding
import com.usacheow.coreui.uikit.helper.ThemeColorsAttrs
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.getColorInt
import com.usacheow.coreui.uikit.helper.ifFalse
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.uikit.helper.resize
import com.usacheow.coreui.R as CoreUiR

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
) : ViewState(CoreUiR.layout.view_badge_tile) {

    companion object {
        fun shimmer() = BadgeShimmerItem()
    }
}

class BadgeShimmerItem : ViewState(CoreUiR.layout.view_badge_shimmer)