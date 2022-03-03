package com.usacheow.coreuiview.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.corecommon.resource.ImageSource
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.base.Populatable
import com.usacheow.coreuiview.adapter.base.ViewState
import com.usacheow.coreuiview.databinding.ViewInformationTileBinding
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.populate
import com.usacheow.coreuiview.R as CoreUiViewR
import com.usacheow.coreuitheme.R as CoreUiThemeR

class InformationTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs), Populatable<InformationTileItem> {

    private val binding by lazy { ViewInformationTileBinding.bind(this) }

    override fun populate(model: InformationTileItem) {
        binding.imageView.populate(model.imageSource)

        binding.additionalLeftView.populate(model.additionalLeftText)
        binding.additionalRightView.populate(model.additionalRightText)
        binding.mainLeftView.populate(model.mainLeftText)
        binding.mainRightView.populate(model.mainRightText)

        doOnClick(model.clickListener)
    }
}

data class InformationTileItem(
    val imageSource: ImageSource? = null,
    val additionalLeftText: TextSource,
    val additionalRightText: TextSource,
    val mainLeftText: TextSource,
    val mainRightText: TextSource,
    val clickListener: (() -> Unit)? = null,
) : ViewState(CoreUiViewR.layout.view_information_tile)