package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewSubtitleTileBinding
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.R as CoreUiR

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
) : ViewState(CoreUiR.layout.view_subtitle_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, bottomLine = false, leftIcon = false, rightIcon = false)
    }
}