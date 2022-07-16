package com.usacheow.featuremain.presentation.viewmodel

import com.usacheow.basesources.LocationSource
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featuremain.data.StubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class AViewModel @Inject constructor(
    private val source: LocationSource,
    private val api: StubApi,
) : SimpleViewModel() {

    private val _uiState = MutableStateFlow(BViewModel.UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<BViewModel.Command>()
    val command = _command.receiveAsFlow()

    var x = 0

//    val state = provider.state.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )
}