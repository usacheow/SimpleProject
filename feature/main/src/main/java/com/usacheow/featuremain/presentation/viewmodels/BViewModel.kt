package com.usacheow.featuremain.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.usacheow.coredata.source.LocationSource
import com.usacheow.corenavigation.base.getArg
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.Serializable

@HiltViewModel
class BViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val locationSource: LocationSource,
) : SimpleViewModel() {

    val index by lazy { savedStateHandle.getArg<Args>()?.index }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()

    var x = 0

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )

    @Serializable
    data class Args(val index: String)
}