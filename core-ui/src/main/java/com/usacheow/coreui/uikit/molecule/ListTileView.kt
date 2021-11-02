package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewListTileBinding
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.populate

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
) : ViewState(CoreUiR.layout.view_list_tile) {

    companion object {
        fun shimmer() = ShimmerTileItem(bottomLine = false, rightIcon = false)
    }
}