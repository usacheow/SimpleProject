package com.usacheow.featurepurchase.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.featurepurchase.databinding.ViewPriceTileBinding
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.TagViewState
import com.usacheow.coreuiview.tools.resource.ThemeColorsAttrs
import com.usacheow.coreuiview.tools.resource.colorByAttr
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.populate
import com.usacheow.coreuiview.tools.resource.toPx
import com.example.featurepurchase.R as FeatureR

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
            colorByAttr(
                when (model.isSelected) {
                    true -> ThemeColorsAttrs.surface
                    false -> ThemeColorsAttrs.surfaceVariant
                }
            )
        )
        binding.cardView.strokeWidth = when (model.isSelected) {
            true -> SELECTED_STROKE_WIDTH_DP.toPx
            false -> UNSELECTED_STROKE_WIDTH_DP.toPx
        }

        binding.periodView.populate(model.period)
        binding.priceView.populate(model.price)
        binding.discountView.populate(model.discount)
        binding.discountLayout.isVisible = model.discount != null
    }
}

data class PriceTileItem(
    var discount: TextSource?,
    val period: TextSource,
    val price: TextSource,
    val buyButtonText: TextSource.Simple,
    val selectListener: () -> Unit,
) : TagViewState(FeatureR.layout.view_price_tile)