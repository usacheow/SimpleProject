package com.usacheow.coreuiview.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.databinding.ViewInformationTileBinding
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.R as CoreUiViewR

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