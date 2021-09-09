package com.usacheow.featureonboarding.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.ViewState
import com.usacheow.featureonboarding.R
import com.usacheow.featureonboarding.databinding.OnBoardingItemViewBinding

class OnBoardingItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs), Populatable<OnBoardingItem> {

    override fun populate(model: OnBoardingItem) {
        val binding = OnBoardingItemViewBinding.bind(this)

        binding.imageView.setImageResource(model.imageId)
        binding.titleView.text = resources.getString(model.titleId)
        binding.descriptionView.text = resources.getString(model.descriptionId)
    }
}

data class OnBoardingItem(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int,
) : ViewState(R.layout.on_boarding_item_view)