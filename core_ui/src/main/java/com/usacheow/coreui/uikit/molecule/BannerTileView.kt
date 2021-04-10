package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.databinding.ViewBannerTileBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

class BannerTileView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<BannerTileItem> {

    private val binding by lazy { ViewBannerTileBinding.bind(this) }

    override fun populate(model: BannerTileItem) {
        binding.bannerClickableView.doOnClick(model.isShimmer, model.clickListener)

        binding.bannerIconView.populate(model.icon)
        binding.bannerTextView.populate(model.text)
    }
}

data class BannerTileItem(
    val icon: ImageSource,
    val text: TextSource,
    val clickListener: (() -> Unit)? = null,
) : ViewType(R.layout.view_banner_tile) {

    companion object {
        fun shimmer() = BannerShimmerItem()
    }
}

class BannerShimmerItem : ViewType(R.layout.view_banner_shimmer)