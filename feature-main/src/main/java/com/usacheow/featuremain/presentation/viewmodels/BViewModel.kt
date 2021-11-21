package com.usacheow.featuremain.presentation.viewmodels

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.usacheow.coredata.location.LocationProvider
import com.usacheow.coreui.utils.navigation.getArgs
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val locationProvider: LocationProvider,
) : SimpleViewModel() {

    val itemNumber by lazy { savedStateHandle.getArgs<Args>()?.itemNumber }

    init {
//        viewModelScope.launch {
//            delay(100)
//            locationProvider.state.onEach {  }.collect()
//        }
    }

    @Parcelize
    data class Args(
        val itemNumber: Int,
    ) : Parcelable
}