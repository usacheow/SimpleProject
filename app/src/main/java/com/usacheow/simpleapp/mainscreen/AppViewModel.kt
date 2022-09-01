package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.corecommon.strings.StringHolder
import com.usacheow.basesources.LocaleSource
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.likeStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

@HiltViewModel
class AppViewModel @Inject constructor(
    localeSource: LocaleSource,
) : SimpleViewModel() {

    val stringHolderState = localeSource.stateFlow
        .map { StringHolder(locale = it) }
        .likeStateFlow(viewModelScope, StringHolder())

    private val _currentFlowState = MutableStateFlow<CurrentFlow>(CurrentFlow.Main)
    val currentFlowState = _currentFlowState.asStateFlow()

    sealed class CurrentFlow {
        object Main : CurrentFlow()
    }
}