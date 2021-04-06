package com.usacheow.featurepurchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.billing.BillingWrapper
import com.usacheow.coreui.billing.Product
import com.usacheow.coreui.billing.ProductType
import com.usacheow.coreui.resources.ResourcesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val resources: ResourcesWrapper,
    private val mapper: ProductsMapper,
    private val billingWrapper: BillingWrapper,
) : ViewModel() {

    private val _stateLiveData = MutableStateFlow(PurchaseStateScreen())
    val stateLiveData = _stateLiveData.asStateFlow()

    private val _buyButtonLiveData = MutableStateFlow("")
    val buyButtonLiveData = _buyButtonLiveData.asStateFlow()

    private val _openPurchaseScreenAction = Channel<Product>()
    val openPurchaseScreenAction = _openPurchaseScreenAction.receiveAsFlow()

    private var selectedProduct: Product? = null

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        billingWrapper.fetchProducts(ProductType.SUBSCRIPTIONS)
        val products = billingWrapper.fetchProducts(ProductType.SUBSCRIPTIONS).data

        val selectedProductIndex = 0

        val mappedProducts = mapper
            .map(products, ::updateBuyButton)
            .map { it.apply { isSelected = selectedProductIndex == 0 } }

        mappedProducts.getOrNull(selectedProductIndex)?.let {
            updateBuyButton(it.buyButtonText.text, products[selectedProductIndex])
        }
        _stateLiveData.emit(PurchaseStateScreen(mappedProducts))
    }

    private fun updateBuyButton(buttonText: String, product: Product) = viewModelScope.launch {
        selectedProduct = product
        _buyButtonLiveData.emit(buttonText)
    }

    fun onBuyClicked() = viewModelScope.launch {
        selectedProduct?.let {
            _openPurchaseScreenAction.send(it)
        }
    }
}

data class PurchaseStateScreen(
    val prices: List<PriceTileItem> = emptyList(),
)