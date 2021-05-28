package com.usacheow.featuremain.presentation.viewmodels

import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor() : SimpleViewModel() {

    var x = 0
}