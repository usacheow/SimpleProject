package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.databinding.ViewHeaderTileBinding
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate

class HeaderTileView
@JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr), Populatable<HeaderTileItem> {

    private val binding by lazy { ViewHeaderTileBinding.bind(this) }

    override fun populate(model: HeaderTileItem) {
        binding.headerView.populate(model.title)
    }
}

data class HeaderTileItem(
    val title: TextSource,
) : ViewType(R.layout.view_header_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(topLine = false, bottomLine = false, leftIcon = false, rightIcon = false)
    }
}