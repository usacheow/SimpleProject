package com.usacheow.featurepurchase.mapper

import com.example.featurepurchase.R
import com.usacheow.corebilling.model.Product
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.utils.TextSource
import com.usacheow.featurepurchase.view.PriceTileItem
import java.util.Currency
import javax.inject.Inject

class ProductsMapper @Inject constructor(
    private val resources: ResourcesWrapper,
) {

    fun map(
        products: List<Product>,
        onProductClicked: (String, Product) -> Unit,
    ): List<PriceTileItem> = products.map { map(it, onProductClicked) }

    private fun map(product: Product, onProductClicked: (String, Product) -> Unit) = PriceTileItem(
        discount = null,
        period = TextSource.Simple(product.details.subscriptionPeriod),
        price = TextSource.Simple(product.getPrice()),
        pricePerMonth = TextSource.Simple(product.getPricePerMonth()),
        buyButtonText = TextSource.Simple(product.getBuyButtonText()),
        clickListener = { onProductClicked(product.getBuyButtonText(), product) },
    )

    private fun Product.getPrice(): String {
        if (details.price == null || details.priceCurrencyCode == null) {
            return ""
        }

        return "${details.price}${Currency.getInstance(details.priceCurrencyCode)}"
    }

    private fun Product.getPricePerMonth(): String {
        if (details.price == null || details.priceCurrencyCode == null) {
            return ""
        }

        val price = "${details.price}${Currency.getInstance(details.priceCurrencyCode)}"
        return resources.getString(R.string.price_per_month_format, price)
    }

    private fun Product.getBuyButtonText(): String {
        val period = details.freeTrialPeriod

        return when (details.freeTrialPeriod) {
            null -> resources.getString(R.string.purchase_buy_button, getPrice(), period)
            else -> resources.getString(R.string.purchase_try_button, period)
        }
    }
}