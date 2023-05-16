package com.usacheow.featureexample.presentation.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class FirstViewModel @Inject constructor() : SimpleViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )
}

@Module
@InstallIn(ActivityComponent::class)
interface FirstViewModelModule {

    @Binds
    @IntoMap
    @ScreenModelKey(FirstViewModel::class)
    fun firstViewModel(viewModel: FirstViewModel): ScreenModel
}