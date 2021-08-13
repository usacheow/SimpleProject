package com.usacheow.featurepurchase.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.featurepurchase.R
import com.example.featurepurchase.databinding.ViewAdvantageTileBinding
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.utils.ImageSource
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.populate

class AdvantageTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr), Populatable<AdvantageTileItem> {

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
) : ViewType(R.layout.view_advantage_tile)