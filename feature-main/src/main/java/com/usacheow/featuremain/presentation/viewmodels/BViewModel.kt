package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    var itemNumber = savedStateHandle.get<Int>("itemNumber")
}