package com.usacheow.featureexample.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.usacheow.corenavigation.AppRoute
import com.usacheow.corenavigation.base.requireArgFromDefaultFormat
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.Serializable

@HiltViewModel
class SecondViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    val args = savedStateHandle.requireArgFromDefaultFormat<AppRoute.ExampleSecond.Args>()

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )

    @Serializable
    data class Args(val index: String)
}