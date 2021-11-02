package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.google.android.material.card.MaterialCardView
import com.usacheow.core.TextSource
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewBadgeTileBinding
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.uikit.helper.resize

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

        binding.root.setCardBackgroundColor(color(model.backgroundColorRes))
        binding.clickableView.doOnClick(model.isShimmer, model.clickListener)

        binding.headerView.apply {
            populate(model.header)
            setTextColor(color(model.textColorRes))
        }
        binding.valueView.apply {
            populate(model.value)
            setTextColor(color(model.textColorRes))
        }
    }
}

data class BadgeTileItem(
    val header: TextSource? = null,
    val value: TextSource,
    val needAdaptWidth: Boolean = true,
    @ColorRes val textColorRes: Int = CoreUiR.color.onSurface,
    @ColorRes val backgroundColorRes: Int = CoreUiR.color.surface,
    val clickListener: (() -> Unit)? = null,
) : ViewState(CoreUiR.layout.view_badge_tile) {

    companion object {
        fun shimmer() = BadgeShimmerItem()
    }
}

class BadgeShimmerItem : ViewState(CoreUiR.layout.view_badge_shimmer)