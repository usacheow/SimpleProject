package com.usacheow.simpleapp

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.usacheow.basesources.LocaleSource
import com.usacheow.corecommon.strings.StringHolder
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.likeStateFlow
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppViewModel @Inject constructor(
    localeSource: LocaleSource,
) : SimpleViewModel() {

    val stringHolderState = localeSource.stateFlow
        .map { StringHolder(locale = it) }
        .likeStateFlow(coroutineScope, StringHolder())

    private val _currentFlowState = MutableStateFlow<CurrentFlow>(CurrentFlow.Main)
    val currentFlowState = _currentFlowState.asStateFlow()

    sealed class CurrentFlow {
        object Main : CurrentFlow()
    }
}

@Module
@InstallIn(ActivityComponent::class)
interface AppViewModelModule {

    @Binds
    @IntoMap
    @ScreenModelKey(AppViewModel::class)
    fun appViewModel(viewModel: AppViewModel): ScreenModel
}