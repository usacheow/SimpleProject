package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.location.LocationProvider
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val locationProvider: LocationProvider,
) : SimpleViewModel() {

    var itemNumber = savedStateHandle.get<Int>(ITEM_NUMBER_KEY)

    companion object {
        const val ITEM_NUMBER_KEY = "itemNumber"
    }

    init {
//        viewModelScope.launch {
//            delay(100)
//            locationProvider.state.onEach {  }.collect()
//        }
    }
}