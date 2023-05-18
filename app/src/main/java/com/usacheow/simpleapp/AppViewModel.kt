package com.usacheow.simpleapp

import cafe.adriel.voyager.core.model.coroutineScope
import com.usacheow.basesources.LocaleSource
import com.usacheow.corecommon.strings.StringHolder
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.likeStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class AppViewModel(
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