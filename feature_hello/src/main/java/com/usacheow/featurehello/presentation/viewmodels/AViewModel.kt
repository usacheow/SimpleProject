package com.usacheow.featurehello.presentation.viewmodels

import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AViewModel @Inject constructor(
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    var x = 0
}