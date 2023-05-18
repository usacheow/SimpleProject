package com.usacheow.featureexample.presentation.viewmodel

import com.usacheow.coreui.viewmodel.SimpleViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SecondViewModel(
    val params: Params,
) : SimpleViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )

    data class Params(
        val index: String,
    )
}