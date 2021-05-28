package com.usacheow.apptest.details

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.google.android.material.card.MaterialCardView
import com.usacheow.apptest.R
import com.usacheow.apptest.databinding.ViewProductCardTileBinding
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

class ProductCardTileView
@JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<ProductCardTileItem> {

    var productElevation: Float
        get() = cardElevation
        set(value) {
            cardElevation = value
        }

    private val binding by lazy { ViewProductCardTileBinding.bind(this) }

    override fun populate(model: ProductCardTileItem) {
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

data class ProductCardTileItem(
    val header: TextSource? = null,
    val value: TextSource,
    @ColorRes val textColorRes: Int = R.color.text,
    @ColorRes val backgroundColorRes: Int = R.color.surface,
    val clickListener: (() -> Unit)? = null,
) : ViewType(R.layout.view_product_card_tile) {

    companion object {
        fun shimmer() = ProductCardTileShimmerItem()
    }
}

class ProductCardTileShimmerItem : ViewType(R.layout.view_badge_shimmer)