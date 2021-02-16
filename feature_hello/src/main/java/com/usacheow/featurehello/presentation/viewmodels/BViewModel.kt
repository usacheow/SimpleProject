package com.usacheow.featurehello.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.usacheow.coreui.resources.ResourcesWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    private val resources: ResourcesWrapper,
) : ViewModel() {

    var x = 0
}