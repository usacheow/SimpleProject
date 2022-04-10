package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.triggerBy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainScreenViewModel @Inject constructor() : SimpleViewModel() {

    private val _action = EventChannel<Action>()
    val action = _action.receiveAsFlow()

    init {
        viewModelScope.launch {
            _action triggerBy Action.OpenAppScreen
        }
    }

    sealed class Action {
        object OpenAppScreen : Action()
    }
}