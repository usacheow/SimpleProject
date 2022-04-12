package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.usacheow.coredata.location.LocationProvider
import com.usacheow.corenavigation.base.getArg
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featuremain.presentation.BScreenArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val locationProvider: LocationProvider,
) : SimpleViewModel() {

    val index by lazy { savedStateHandle.getArg<BScreenArg>()?.index }

    var x = 0
}