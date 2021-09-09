package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.databinding.ViewInformationTileBinding
import com.usacheow.coreui.utils.ImageSource
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.doOnClick

class InformationTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), Populatable<InformationTileItem> {

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
) : ViewState(R.layout.view_information_tile)