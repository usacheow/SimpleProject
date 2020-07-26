package com.usacheow.featurehello.presentation.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.usacheow.coreui.resources.ResourcesWrapper

class AViewModel
@ViewModelInject constructor(
    private val resources: ResourcesWrapper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var x = 0
}