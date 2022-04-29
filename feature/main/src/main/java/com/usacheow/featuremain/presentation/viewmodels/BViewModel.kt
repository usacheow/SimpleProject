package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.usacheow.coredata.source.LocationSource
import com.usacheow.corenavigation.base.getArg
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featuremain.presentation.BScreenArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val locationSource: LocationSource,
) : SimpleViewModel() {

    val index by lazy { savedStateHandle.getArg<BScreenArg>()?.index }

    var x = 0
}