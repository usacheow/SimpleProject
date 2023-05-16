package com.usacheow.featureexample.presentation.viewmodel

import cafe.adriel.voyager.hilt.ScreenModelFactory
import cafe.adriel.voyager.hilt.ScreenModelFactoryKey
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.Binds
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SecondViewModel @AssistedInject constructor(
    @Assisted val index: String,
) : SimpleViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _command = Channel<Command>()
    val command = _command.receiveAsFlow()

    sealed class Command

    data class UiState(
        val isLoading: Boolean = false,
    )

    @AssistedFactory
    interface Factory : ScreenModelFactory {
        fun create(index: String): SecondViewModel
    }
}

@Module
@InstallIn(ActivityComponent::class)
interface SecondViewModelModule {

    @Binds
    @IntoMap
    @ScreenModelFactoryKey(SecondViewModel.Factory::class)
    fun secondViewModelFactory(factory: SecondViewModel.Factory): ScreenModelFactory
}