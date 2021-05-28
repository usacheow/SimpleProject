package com.usacheow.apptest.details

import androidx.lifecycle.viewModelScope
import com.usacheow.apptest.R
import com.usacheow.coreui.adapter.base.ViewType
import com.usacheow.coreui.uikit.molecule.InformationTileItem
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : SimpleViewModel() {

    private val _productType = MutableStateFlow("")
    val productType = _productType.asStateFlow()

    private val _balance = MutableStateFlow("")
    val balance = _balance.asStateFlow()

    private val _state = MutableStateFlow<DetailsState>(DetailsState.Data(emptyList(), 0))
    val state = _state.asStateFlow()

    private val _additionalState = MutableStateFlow<DetailsAdditionalState>(DetailsAdditionalState.Data(emptyList(), emptyList()))
    val additionalState = _additionalState.asStateFlow()

    private var currentJob: Job? = null
    private var selectedProductIndex = 0

    init {
        showShimmers()
        loadData()
        viewModelScope.launch {
            _state.emit(DetailsState.Data(products, 0))
            _productType.emit(types[selectedProductIndex])
            _balance.emit(balances[selectedProductIndex])
        }
    }

    fun onRefresh() {
        showShimmers()
        loadData()
    }

    fun onProductSelecting(position: Int) {
        if (selectedProductIndex == position % products.size) {
            return
        }

        selectedProductIndex = position % products.size
        viewModelScope.launch {
            _productType.emit(types[selectedProductIndex])
            _balance.emit(balances[selectedProductIndex])
        }
        showShimmers()
        loadData()
    }

    private fun showShimmers() = viewModelScope.launch {
        _additionalState.emit(
            DetailsAdditionalState.Loading(getWarningsShimmers(), getOperationsShimmers())
        )
    }

    private fun loadData() {
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            delay(1000)
            _additionalState.emit(DetailsAdditionalState.Data(getWarnings(selectedProductIndex), getOperations()))
        }
    }

    private fun getWarningsShimmers() = List(1) { WarningTileItem.shimmer() }

    private fun getWarnings(position: Int) = List((position + 1) % 3) {
        WarningTileItem(
            value = TextString("Warning title"),
            topDescription = TextString("Warning description"),
        )
    }

    private fun getOperationsShimmers() = List(3) { ListTileItem.shimmer() }

    private fun getOperations() = List(3) {
        InformationTileItem(
            imageSource = ImageRes(R.drawable.demo_avatar),
            additionalLeftText = TextString("Транспорт"),
            additionalRightText = TextString("09/05/2021"),
            mainLeftText = TextString("Яндекс Такси"),
            mainRightText = TextString("237 Р"),
        )
    }
}

sealed class DetailsState {
    data class Data(
        val products: List<ViewType>,
        val selectedProductIndex: Int,
    ) : DetailsState()
}

sealed class DetailsAdditionalState {
    data class Data(
        val warnings: List<ViewType>,
        val operations: List<ViewType>,
    ) : DetailsAdditionalState()

    data class Loading(
        val warnings: List<ViewType>,
        val operations: List<ViewType>,
    ) : DetailsAdditionalState()
}

private val types = listOf(
    "Дебетовая карта",
    "Кредитная карта",
    "Вклад",
    "Счёт",
    "Кредит",
)

private val balances = listOf(
    "132 000 P",
    "58 000 P",
    "74 000 P",
    "13 000 P",
    "205 000 P",
)

private val products = listOf(
    ProductCardTileItem(
        header = TextString("Product card 0"),
        value = TextString("Number *1234"),
        clickListener = {}),
    ProductCardTileItem(
        header = TextString("Product card 1"),
        value = TextString("Number *1234"),
        clickListener = {}),
    ProductCardTileItem(
        header = TextString("Product card 2"),
        value = TextString("Number *1234"),
        clickListener = {}),
    ProductCardTileItem(
        header = TextString("Product card 3"),
        value = TextString("Number *1234"),
        clickListener = {}),
    ProductCardTileItem(
        header = TextString("Product card 4"),
        value = TextString("Number *1234"),
        clickListener = {}),
)