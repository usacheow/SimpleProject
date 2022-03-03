package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewSubtitleTileBinding
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.populate
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