package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import com.facebook.shimmer.ShimmerFrameLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewListTileBinding
import com.usacheow.coreui.databinding.ViewShimmerTileBinding
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

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
) : ViewType(R.layout.view_shimmer_tile)