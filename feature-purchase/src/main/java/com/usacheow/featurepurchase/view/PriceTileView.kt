package com.usacheow.featurepurchase.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.featurepurchase.databinding.ViewPriceTileBinding
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.TagViewState
import com.usacheow.coreui.uikit.helper.color
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.uikit.helper.toPx
import com.example.featurepurchase.R as FeatureR
import com.usacheow.coreui.R as CoreUiR

private const val SELECTED_STROKE_WIDTH_DP = 2
private const val UNSELECTED_STROKE_WIDTH_DP = 0

class PriceTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs), Populatable<PriceTileItem> {

    private val binding by lazy { ViewPriceTileBinding.bind(this) }

    override fun populate(model: PriceTileItem) {
        binding.clickableView.doOnClick {
            if (!model.isSelected) {
                model.selectListener()
            }
            model.clickListener()
        }

        binding.cardView.setCardBackgroundColor(
            color(
                when (model.isSelected) {
                    true -> CoreUiR.color.surface
                    false -> CoreUiR.color.surfaceVariant
                }
            )
        )
        binding.cardView.strokeWidth = when (model.isSelected) {
            true -> SELECTED_STROKE_WIDTH_DP.toPx
            false -> UNSELECTED_STROKE_WIDTH_DP.toPx
        }

        binding.periodView.populate(model.period)
        binding.priceView.populate(model.price)
        binding.pricePerMonthView.populate(model.pricePerMonth)
        binding.discountView.populate(model.discount)
        binding.discountLayout.isVisible = model.discount != null
    }
}

data class PriceTileItem(
    var discount: TextSource?,
    val period: TextSource,
    val price: TextSource,
    val pricePerMonth: TextSource,
    val buyButtonText: TextSource.Simple,
    val selectListener: () -> Unit,
) : TagViewState(FeatureR.layout.view_price_tile)