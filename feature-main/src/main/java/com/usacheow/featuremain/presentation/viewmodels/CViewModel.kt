package com.usacheow.featuremain.presentation.viewmodels

import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CViewModel @Inject constructor() : SimpleViewModel() {

    var x = 0
}