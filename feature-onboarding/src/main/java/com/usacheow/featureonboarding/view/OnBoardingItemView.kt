package com.usacheow.featureonboarding.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.featureonboarding.databinding.OnBoardingItemViewBinding
import com.usacheow.featureonboarding.R as FeatureR

class OnBoardingItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs), Populatable<OnBoardingItem> {

    override fun populate(model: OnBoardingItem) {
        val binding = OnBoardingItemViewBinding.bind(this)

        binding.imageView.populate(model.image)
        binding.titleView.populate(model.title)
        binding.descriptionView.populate(model.description)
    }
}

data class OnBoardingItem(
    val image: ImageSource,
    val title: TextSource,
    val description: TextSource,
) : ViewState(FeatureR.layout.on_boarding_item_view)