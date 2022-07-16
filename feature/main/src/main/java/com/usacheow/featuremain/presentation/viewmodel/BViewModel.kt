package com.usacheow.featuremain.presentation.viewmodel

import com.usacheow.basesources.LocationSource
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class BViewModel @Inject constructor(
    private val locationSource: LocationSource,
) : SimpleViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()

    var x = 0

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )
}