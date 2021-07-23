package com.usacheow.featurepurchase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usacheow.corebilling.PurchaseStateProvider
import com.usacheow.corebilling.model.Product
import com.usacheow.featurepurchase.mapper.ProductsMapper
import com.usacheow.featurepurchase.view.PriceTileItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val mapper: ProductsMapper,
    private val purchaseStateProvider: PurchaseStateProvider,
) : ViewModel() {

    private val _productsState = MutableStateFlow(PurchaseStateScreen())
    val productsState = _productsState.asStateFlow()

    private val _buyButtonTextState = MutableStateFlow("")
    val buyButtonTextState = _buyButtonTextState.asStateFlow()

    private val _openPurchaseScreenAction = Channel<Product>()
    val openPurchaseScreenAction = _openPurchaseScreenAction.receiveAsFlow()

    private var selectedProduct: Product? = null

    init {
        loadData()
    }

    private fun loadData() {
        purchaseStateProvider.subscriptionsFlow.onEach {
            val products = it.data
            val selectedProductIndex = 0

            val mappedProducts = mapper
                .map(products, ::updateBuyButton)
                .map { price -> price.apply { isSelected = selectedProductIndex == 0 } }

            mappedProducts.getOrNull(selectedProductIndex)?.let { price ->
                updateBuyButton(price.buyButtonText.text, products[selectedProductIndex])
            }
            _productsState.emit(PurchaseStateScreen(mappedProducts))
        }.launchIn(viewModelScope)
    }

    private fun updateBuyButton(buttonText: String, product: Product) = viewModelScope.launch {
        selectedProduct = product
        _buyButtonTextState.emit(buttonText)
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