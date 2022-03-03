package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewHeaderTileBinding
import com.usacheow.coreuiview.helper.populate
import com.usacheow.coreuiview.R as CoreUiViewR

class HeaderTileView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : LinearLayout(context, attributeSet), Populatable<HeaderTileItem> {

    private val binding by lazy { ViewHeaderTileBinding.bind(this) }

    override fun populate(model: HeaderTileItem) {
        binding.headerView.populate(model.title)
    }
}

data class HeaderTileItem(
    val title: TextSource,
) : ViewState(CoreUiViewR.layout.view_header_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, bottomLine = false, leftIcon = false, rightIcon = false)
    }
}