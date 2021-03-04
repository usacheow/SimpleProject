package com.usacheow.coreui.uikit.molecule

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewInformationTileBinding
import com.usacheow.coreui.utils.ImageSource
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.setListenerIfNeed

class InformationTileView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), Populatable<InformationTileItem> {

    private val binding by lazy { ViewInformationTileBinding.bind(this) }

    override fun populate(model: InformationTileItem) {
        binding.imageView.populate(model.imageSource)

        binding.leftTopView.populate(model.topLeftText)
        binding.rightTopView.populate(model.rightTopText)
        binding.leftMainView.populate(model.leftMain)
        binding.rightMainView.populate(model.rightMain)

        setListenerIfNeed(model.clickListener)
    }
}

data class InformationTileItem(
    val imageSource: ImageSource? = null,
    val topLeftText: TextSource,
    val rightTopText: TextSource,
    val leftMain: TextSource,
    val rightMain: TextSource,
    val clickListener: (() -> Unit)? = null,
) : ViewType(R.layout.view_information_tile)