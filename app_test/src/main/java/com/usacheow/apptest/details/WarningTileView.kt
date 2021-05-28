package com.usacheow.apptest.details

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import com.google.android.material.card.MaterialCardView
import com.usacheow.apptest.R
import com.usacheow.apptest.databinding.ViewWarningTileBinding
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.databinding.ViewListTileBinding
import com.usacheow.coreui.utils.EmptyInfo
import com.usacheow.coreui.utils.IconInfo
import com.usacheow.coreui.utils.ImageInfo
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.apply
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx

private const val ICON_PADDING_DP = 4
private const val DEFAULT_PADDING_DP = 0

class WarningTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), Populatable<WarningTileItem> {

    private val binding by lazy { ViewWarningTileBinding.bind(this) }

    override fun populate(model: WarningTileItem) {
        binding.valueView.populate(model.value)
        binding.topDescriptionView.populate(model.topDescription)
        binding.bottomDescriptionView.populate(model.bottomDescription)
        doOnClick(model.clickListener)
    }

    private fun ImageView.updatePadding(imageInfo: ImageInfo) {
        val padding = when (imageInfo) {
            is IconInfo -> ICON_PADDING_DP
            else -> DEFAULT_PADDING_DP
        }.toPx
        updatePadding(padding, padding, padding, padding)
    }
}

data class WarningTileItem(
    val value: TextSource,
    val topDescription: TextSource? = null,
    val bottomDescription: TextSource? = null,
    val clickListener: (() -> Unit)? = null,
) : ViewType(R.layout.view_warning_tile) {

    companion object {
        fun shimmer() = WarningShimmerTileItem
    }
}