package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewShimmerTileBinding
import com.usacheow.coreuiview.helper.setShimmer
import com.usacheow.coreuiview.R as CoreUiViewR

class ShimmerTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ShimmerFrameLayout(context, attrs), Populatable<ShimmerTileItem> {

    private val binding by lazy { ViewShimmerTileBinding.bind(this) }

    override fun populate(model: ShimmerTileItem) {
        binding.leftIconView.isVisible = model.leftIcon
        binding.topLineView.isVisible = model.topLine
        binding.middleLineView.isVisible = model.middleLine
        binding.bottomLineView.isVisible = model.bottomLine
        binding.rightIconView.isVisible = model.rightIcon

        setShimmer(true)
    }
}

data class ShimmerTileItem(
    val leftIcon: Boolean = true,
    val rightIcon: Boolean = true,
    val topLine: Boolean = true,
    val middleLine: Boolean = true,
    val bottomLine: Boolean = true,
) : ViewState(CoreUiViewR.layout.view_shimmer_tile)