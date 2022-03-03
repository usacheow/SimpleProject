package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.corecommon.resource.ImageSource
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewListTileBinding
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.populate
import com.usacheow.coreuiview.R as CoreUiViewR
import com.usacheow.coreuitheme.R as CoreUiThemeR

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