package com.usacheow.featurepurchase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usacheow.corebilling.PurchaseStateProvider
import com.usacheow.corebilling.model.Product
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.featurepurchase.mapper.ProductsMapper
import com.usacheow.featurepurchase.view.PriceTileItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _closeScreenAction = Channel<SimpleAction>()
    val closeScreenAction = _closeScreenAction.receiveAsFlow()

    private var selectedProduct: Product? = null

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        val selectedProductIndex = 0
        val products = purchaseStateProvider.getSubscribeProducts().data
        if (products == null) {
            _closeScreenAction.send(SimpleAction)
            return@launch
        }

        val mappedProducts = mapper
            .map(products, ::updateBuyButton)
            .map { price -> price.apply { isSelected = selectedProductIndex == 0 } }

        mappedProducts.getOrNull(selectedProductIndex)?.let { price ->
            updateBuyButton(price.buyButtonText.text, products[selectedProductIndex])
        }
        _productsState.emit(PurchaseStateScreen(mappedProducts))
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