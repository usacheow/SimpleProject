package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.triggerBy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AppViewModel @Inject constructor() : SimpleViewModel() {

    private val _initialScreenEvent = EventChannel<Action>()
    val initialScreenEvent = _initialScreenEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _initialScreenEvent triggerBy Action.OpenAppScreen
        }
    }

    sealed class Action {
        object OpenAppScreen : Action()
        object OpenOnBoardingScreen : Action()
    }
}