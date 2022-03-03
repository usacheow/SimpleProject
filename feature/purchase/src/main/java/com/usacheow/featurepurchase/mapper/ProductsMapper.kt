package com.usacheow.featurepurchase.mapper

import android.content.Context
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.usacheow.corecommon.resource.ResourcesWrapper
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.corebilling.model.Product
import com.usacheow.coreuitheme.R as CoreUiThemeR
import com.usacheow.coreuiview.helper.ThemeColorsAttrs
import com.usacheow.coreuiview.helper.colorByAttr
import com.usacheow.featurepurchase.view.PriceTileItem
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Currency
import javax.inject.Inject
import com.usacheow.corecommon.R as CoreR

class ProductsMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val resources: ResourcesWrapper,
) {

    fun map(
        products: List<Product>,
        onProductClicked: (String, Product) -> Unit,
    ): List<PriceTileItem> = products.map { map(it, onProductClicked) }

    private fun map(product: Product, onProductClicked: (String, Product) -> Unit) = PriceTileItem(
        discount = product.getDiscount(),
        period = product.getPeriod(),
        price = product.getPrice(),
        buyButtonText = TextSource.Simple(product.getBuyButtonText()),
        selectListener = { onProductClicked(product.getBuyButtonText(), product) },
    )

    private fun Product.getDiscount(): TextSource? {
        return null
    }

    private fun Product.getPrice(): TextSource {
        val text = buildSpannedString {
            inSpans(
                TextAppearanceSpan(context, CoreUiThemeR.style.Simple_Text_Headline_M),
                ForegroundColorSpan(context.colorByAttr(ThemeColorsAttrs.symbolPrimary)),
            ) {
                append(getPriceValue())
            }
            inSpans(
                TextAppearanceSpan(context, CoreUiThemeR.style.Simple_Text_Body_S),
                ForegroundColorSpan(context.colorByAttr(ThemeColorsAttrs.symbolSecondary)),
            ) {
                append(" / ${getPeriod().toCharSequence(resources)}")
            }
        }
        return TextSource.Spanned(text)
    }

    private fun Product.getPriceValue(): String {
        return "${details.priceAmountMicros.div(1_000_000)}" +
                Currency.getInstance(details.priceCurrencyCode).symbol
    }

    private fun Product.getPeriod(): TextSource {
        val rawPeriod = details.subscriptionPeriod

        fun getCount() = rawPeriod.filter { it.isDigit() }.toIntOrNull() ?: 0

        val priceFormatRes = when {
            rawPeriod.contains("D") -> CoreR.plurals.purchase_period_day_format
            rawPeriod.contains("W") -> CoreR.plurals.purchase_period_week_format
            rawPeriod.contains("M") -> CoreR.plurals.purchase_period_month_format
            rawPeriod.contains("Y") -> CoreR.plurals.purchase_period_year_format
            else -> return TextSource.Simple("")
        }
        return TextSource.FormattedPlural(priceFormatRes, getCount())
    }

    private fun Product.getBuyButtonText(): String {
        return resources.getString(
            CoreR.string.purchase_buy_button,
            getPriceValue(),
            getPeriod().toCharSequence(resources),
        )
    }
}