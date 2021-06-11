package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    private val resourcesWrapper: ResourcesWrapper,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    var itemNumber = savedStateHandle.get<Int>("itemNumber")
}