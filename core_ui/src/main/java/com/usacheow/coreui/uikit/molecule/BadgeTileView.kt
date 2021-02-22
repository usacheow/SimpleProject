package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewBadgeTileBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val HEADER_SHIMMER_WIDTH_DP = 60
private const val VALUE_SHIMMER_WIDTH_DP = 100

class BadgeTileView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<BadgeTileItem> {

    private val binding by lazy { ViewBadgeTileBinding.bind(this) }

    private val headerShimmerWidthPx by lazy { HEADER_SHIMMER_WIDTH_DP.toPx }
    private val valueShimmerWidthPx by lazy { VALUE_SHIMMER_WIDTH_DP.toPx }

    override fun populate(model: BadgeTileItem) {
        val containerWidth = when (model.needAdapt) {
            true -> ViewGroup.LayoutParams.WRAP_CONTENT
            false -> ViewGroup.LayoutParams.MATCH_PARENT
        }
        binding.root.resize(widthPx = containerWidth, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.cardView.resize(widthPx = containerWidth, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.clickableView.resize(widthPx = containerWidth, heightPx = ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.cardView.setCardBackgroundColor(color(model.backgroundColorRes))
        binding.clickableView.setListenerIfNeed(model.isShimmer, model.clickAction)

        updateMargins(model.margin)
        populateHeader(model)
        populateValue(model)
        setShimmer(model.isShimmer)
    }

    private fun populateHeader(model: BadgeTileItem) = with(binding.headerView) {
        if (model.isShimmer) {
            showShimmer(widthPx = headerShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            populate(model.header)
            setTextColor(color(model.textColorRes))
        }
    }

    private fun populateValue(model: BadgeTileItem) = with(binding.valueView) {
        setLines(when (model.needAdapt) {
            true -> 1
            false -> 2
        })

        if (model.isShimmer) {
            showShimmer(widthPx = valueShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            populate(model.value)
            setTextColor(color(model.textColorRes))
        }
    }
}

data class BadgeTileItem(
    val header: TextSource? = null,
    val value: TextSource,
    val needAdapt: Boolean = true,
    @ColorRes val textColorRes: Int = R.color.colorText,
    @ColorRes val backgroundColorRes: Int = R.color.colorGreyCard,
    val margin: Margin2 = Margin2(8.toPx, 8.toPx),
    val clickAction: (() -> Unit)? = null,
) : ViewType(R.layout.view_badge_tile) {

    companion object {
        fun shimmer() = BadgeTileItem(value = TextString("")).apply { isShimmer = true }
    }
}