package com.usacheow.coreuikit.onboarding

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import kotlinx.android.synthetic.main.on_boarding_item_view.view.onboardingItemDescriptionView
import kotlinx.android.synthetic.main.on_boarding_item_view.view.onboardingItemImageView
import kotlinx.android.synthetic.main.on_boarding_item_view.view.onboardingItemTitleView

class OnBoardingItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : LinearLayout(context, attrs), Populatable<OnBoardingItem> {

    override fun populate(model: OnBoardingItem) {
        onboardingItemImageView.setImageResource(model.imageId)
        onboardingItemTitleView.text = resources.getString(model.titleId)
        onboardingItemDescriptionView.text = resources.getString(model.descriptionId)
    }
}

data class OnBoardingItem(
    @DrawableRes val imageId: Int,
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int
) : ViewType(R.layout.on_boarding_item_view)