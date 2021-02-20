package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewBadgeBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val HEADER_SHIMMER_WIDTH_DP = 60
private const val VALUE_SHIMMER_WIDTH_DP = 100

class BadgeItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<BadgeItem> {

    private val binding by lazy { ViewBadgeBinding.bind(this) }

    private val headerShimmerWidthPx by lazy { HEADER_SHIMMER_WIDTH_DP.toPx }
    private val valueShimmerWidthPx by lazy { VALUE_SHIMMER_WIDTH_DP.toPx }

    override fun populate(model: BadgeItem) {
        binding.cardView.setCardBackgroundColor(color(model.backgroundColorRes))
        binding.clickableView.setListenerIfNeed(model.isShimmer, model.clickAction)

        updateMargins(model.margin)
        populateHeader(model)
        populateValue(model)
        setShimmer(model.isShimmer)
    }

    private fun populateHeader(model: BadgeItem) = with (binding.headerView) {
        if (model.isShimmer) {
            showShimmer(widthPx = headerShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            populate(model.header)
            setTextColor(color(model.textColorRes))
        }
    }

    private fun populateValue(model: BadgeItem) = with (binding.valueView) {
        if (model.isShimmer) {
            showShimmer(widthPx = valueShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            populate(model.value)
            setTextColor(color(model.textColorRes))
        }
    }
}

data class BadgeItem(
    val header: TextSource? = null,
    val value: TextSource,
    @ColorRes val textColorRes: Int = R.color.colorText,
    @ColorRes val backgroundColorRes: Int = R.color.colorGreyCard,
    val margin: Margin2 = Margin2(8.toPx, 8.toPx),
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_badge) {

    companion object {
        fun shimmer() = BadgeItem(value = TextString("")).apply { isShimmer = true }
    }
}