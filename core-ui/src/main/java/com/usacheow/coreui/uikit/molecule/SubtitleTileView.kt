package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.core.TextSource
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewSubtitleTileBinding
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.populate

class SubtitleTileView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attributeSet, defStyleAttr), Populatable<SubtitleTileItem> {

    private val binding by lazy { ViewSubtitleTileBinding.bind(this) }

    override fun populate(model: SubtitleTileItem) {
        binding.headerView.populate(model.title)
        binding.actionButton.populate(model.actionTitle)
        binding.actionButton.doOnClick(model.clickListener)
    }
}

data class SubtitleTileItem(
    val title: TextSource,
    val actionTitle: TextSource? = null,
    val clickListener: (() -> Unit)? = null,
) : ViewState(R.layout.view_subtitle_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, bottomLine = false, leftIcon = false, rightIcon = false)
    }
}