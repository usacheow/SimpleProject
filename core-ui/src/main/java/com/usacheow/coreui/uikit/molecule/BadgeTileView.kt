package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.google.android.material.card.MaterialCardView
import com.usacheow.core.TextSource
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewBadgeTileBinding
import com.usacheow.coreui.utils.view.color
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.populate
import com.usacheow.coreui.utils.view.resize
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class BadgeTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<BadgeTileItem> {

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
    @ColorRes val textColorRes: Int = R.color.text,
    @ColorRes val backgroundColorRes: Int = R.color.surface,
    val clickListener: (() -> Unit)? = null,
) : ViewState(R.layout.view_badge_tile) {

    companion object {
        fun shimmer() = BadgeShimmerItem()
    }
}

class BadgeShimmerItem : ViewState(R.layout.view_badge_shimmer)