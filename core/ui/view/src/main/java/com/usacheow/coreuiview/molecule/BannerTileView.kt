package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.corecommon.resource.ImageSource
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewBannerTileBinding
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.ifFalse
import com.usacheow.coreuiview.helper.populate
import com.usacheow.coreuiview.R as CoreUiViewR

class BannerTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialCardView(context, attrs), Populatable<BannerTileItem> {

    private val binding by lazy { ViewBannerTileBinding.bind(this) }

    override fun populate(model: BannerTileItem) {
        binding.bannerClickableView.doOnClick(model.clickListener.ifFalse(model.isShimmer))

        binding.bannerIconView.populate(model.icon)
        binding.bannerTextView.populate(model.text)
    }
}

data class BannerTileItem(
    val icon: ImageSource,
    val text: TextSource,
    val clickListener: (() -> Unit)? = null,
) : ViewState(CoreUiViewR.layout.view_banner_tile) {

    companion object {
        fun shimmer() = BannerShimmerItem()
    }
}

class BannerShimmerItem : ViewState(CoreUiViewR.layout.view_banner_shimmer)