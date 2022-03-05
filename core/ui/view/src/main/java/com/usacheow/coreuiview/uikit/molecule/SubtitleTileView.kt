package com.usacheow.coreuiview.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.databinding.ViewSubtitleTileBinding
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.R as CoreUiViewR

class SubtitleTileView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
) : LinearLayout(context, attributeSet), Populatable<SubtitleTileItem> {

    private val binding by lazy { ViewSubtitleTileBinding.bind(this) }

    override fun populate(model: SubtitleTileItem) {
        binding.headerView.populate(model.title)
        binding.actionButton.populate(model.actionText)
        binding.actionButton.doOnClick(model.clickListener)
    }
}

data class SubtitleTileItem(
    val title: TextSource,
    val actionText: TextSource? = null,
    val clickListener: (() -> Unit)? = null,
) : ViewState(CoreUiViewR.layout.view_subtitle_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, bottomLine = false, leftIcon = false, rightIcon = false)
    }
}