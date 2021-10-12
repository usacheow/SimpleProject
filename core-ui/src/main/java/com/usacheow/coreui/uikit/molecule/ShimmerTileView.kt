package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewShimmerTileBinding
import com.usacheow.coreui.uikit.helper.setShimmer

class ShimmerTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShimmerFrameLayout(context, attrs, defStyleAttr), Populatable<ShimmerTileItem> {

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
) : ViewState(CoreUiR.layout.view_shimmer_tile)