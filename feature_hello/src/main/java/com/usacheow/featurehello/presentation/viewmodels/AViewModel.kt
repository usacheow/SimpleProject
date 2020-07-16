package com.usacheow.featurehello.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.usacheow.coreui.resources.ResourcesWrapper
import javax.inject.Inject

class AViewModel
@Inject constructor(
    private val resources: ResourcesWrapper
) : ViewModel() {

    var x = 0
}