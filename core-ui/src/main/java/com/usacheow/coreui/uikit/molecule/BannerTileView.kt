package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewBannerTileBinding
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.ifFalse
import com.usacheow.coreui.uikit.helper.populate

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
) : ViewState(CoreUiR.layout.view_banner_tile) {

    companion object {
        fun shimmer() = BannerShimmerItem()
    }
}

class BannerShimmerItem : ViewState(CoreUiR.layout.view_banner_shimmer)