package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewSubtitleTileBinding
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.setListenerIfNeed

class SubtitleTileView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr), Populatable<SubtitleTileItem> {

    private val binding by lazy { ViewSubtitleTileBinding.bind(this) }

    override fun populate(model: SubtitleTileItem) {
        binding.headerView.populate(model.title)
        binding.actionButton.populate(model.actionTitle)
        binding.actionButton.setListenerIfNeed(model.clickListener)
    }
}

data class SubtitleTileItem(
    val title: TextSource,
    val actionTitle: TextSource? = null,
    val clickListener: (() -> Unit)? = null,
) : ViewType(R.layout.view_subtitle_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, bottomLine = false, leftIcon = false, rightIcon = false)
    }
}