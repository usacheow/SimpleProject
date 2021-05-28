package com.usacheow.featurepurchase.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.featurepurchase.R
import com.example.featurepurchase.databinding.ViewPriceTileBinding
import com.usacheow.coreui.adapter.base.Populatable
import com.usacheow.coreui.adapter.base.TagViewType
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.*

private const val SELECTED_STROKE_WIDTH_DP = 2
private const val UNSELECTED_STROKE_WIDTH_DP = 0

class PriceTileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), Populatable<PriceTileItem> {

    private val binding by lazy { ViewPriceTileBinding.bind(this) }

    override fun populate(model: PriceTileItem) {
        binding.clickableView.doOnClick {
            if (!model.isSelected) {
                model.clickListener()
            }
            model.onSelectAction()
        }

        binding.cardView.setCardBackgroundColor(
            color(
                when (model.isSelected) {
                    true -> R.color.surface
                    false -> R.color.surfaceSecondaryVariant
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
    val buyButtonText: TextString,
    val clickListener: () -> Unit,
) : TagViewType(R.layout.view_price_tile)