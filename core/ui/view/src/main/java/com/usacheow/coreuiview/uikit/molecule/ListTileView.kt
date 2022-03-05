package com.usacheow.coreuiview.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.databinding.ViewListTileBinding
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.R as CoreUiViewR

class ListTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs), Populatable<ListTileItem> {

    private val binding by lazy { ViewListTileBinding.bind(this) }

    override fun populate(model: ListTileItem) {
        binding.valueView.populate(model.value)
        binding.topDescriptionView.populate(model.topDescription)
        binding.bottomDescriptionView.populate(model.bottomDescription)
        binding.leftIconView.populate(model.leftImageInfo)
        binding.rightIconView.populate(model.rightImageInfo)
        doOnClick(model.clickListener)
    }
}

data class ListTileItem(
    val leftImageInfo: ImageSource = ImageSource.Empty,
    val rightImageInfo: ImageSource = ImageSource.Empty,
    val value: TextSource,
    val topDescription: TextSource? = null,
    val bottomDescription: TextSource? = null,
    val clickListener: (() -> Unit)? = null,
) : ViewState(CoreUiViewR.layout.view_list_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(bottomLine = false, rightIcon = false)
    }
}