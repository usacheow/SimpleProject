package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewInformationTileBinding
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.R as CoreUiR

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
) : ViewState(CoreUiR.layout.view_information_tile)