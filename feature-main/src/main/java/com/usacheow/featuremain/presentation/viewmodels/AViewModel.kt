package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ApiError
import com.usacheow.core.Effect
import com.usacheow.core.tryRun
import com.usacheow.coredata.location.LocationProvider
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AViewModel @Inject constructor(
    private val provider: LocationProvider,
) : SimpleViewModel() {

    var x = 0

//    val state = provider.state.shareIn(viewModelScope, SharingStarted.WhileSubscribed())
}