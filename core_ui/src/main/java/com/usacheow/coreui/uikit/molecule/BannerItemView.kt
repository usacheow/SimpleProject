package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewBannerBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val TEXT_SHIMMER_WIDTH_DP = 144

class BannerItemView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<BannerItem> {

    private val binding by lazy { ViewBannerBinding.bind(this) }

    private val textShimmerWidthPx by lazy { TEXT_SHIMMER_WIDTH_DP.toPx }

    override fun populate(model: BannerItem) {
        binding.bannerClickableView.setListenerIfNeed(model.isShimmer, model.clickAction)

        updateMargins(model.margin)
        populateIcon(model)
        populateText(model)
        setShimmer(model.isShimmer)
    }

    private fun populateText(model: BannerItem) = with (binding.bannerTextView) {
        if (model.isShimmer) {
            showShimmer(widthPx = textShimmerWidthPx)
        } else {
            hideShimmer(widthPx = ViewGroup.LayoutParams.WRAP_CONTENT)
            populate(model.text)
        }
    }

    private fun populateIcon(model: BannerItem) = with (binding.bannerIconView) {
        if (model.isShimmer) {
            showCircleShimmer()
        } else {
            hideShimmer(model.icon)
        }
    }
}

data class BannerItem(
    val icon: ImageSource,
    val text: TextSource,
    val margin: Margin2 = Margin2(8.toPx, 8.toPx),
    val clickAction: (() -> Unit)? = null
) : ViewType(R.layout.view_banner) {

    companion object {
        fun shimmer() = BannerItem(icon = ImageEmpty, text = TextString("")).apply { isShimmer = true }
    }
}