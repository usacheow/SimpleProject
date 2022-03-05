package com.usacheow.featurepurchase.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.featurepurchase.databinding.ViewAdvantageTileBinding
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.tools.populate
import com.example.featurepurchase.R as FeatureR

class AdvantageTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs), Populatable<AdvantageTileItem> {

    private val binding by lazy { ViewAdvantageTileBinding.bind(this) }

    override fun populate(model: AdvantageTileItem) {
        binding.imageView.populate(model.image)
        binding.titleView.populate(model.title)
        binding.infoView.populate(model.info)
    }
}

data class AdvantageTileItem(
    val image: ImageSource,
    val title: TextSource,
    val info: TextSource?,
) : ViewState(FeatureR.layout.view_advantage_tile)