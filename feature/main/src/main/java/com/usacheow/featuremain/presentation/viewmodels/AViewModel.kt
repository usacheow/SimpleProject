package com.usacheow.featuremain.presentation.viewmodels

import com.usacheow.coredata.source.LocationSource
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featuremain.data.StubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AViewModel @Inject constructor(
    private val source: LocationSource,
    private val api: StubApi,
) : SimpleViewModel() {

    var x = 0

//    val state = provider.state.shareIn(viewModelScope, SharingStarted.WhileSubscribed())
}