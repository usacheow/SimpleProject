package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AppViewModel @Inject constructor() : SimpleViewModel() {

    private val _initialScreenEvent = Channel<Action>()
    val initialScreenEvent = _initialScreenEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _initialScreenEvent publish Action.OpenAppScreen
        }
    }

    sealed class Action {
        object OpenAppScreen : Action()
        object OpenOnBoardingScreen : Action()
    }
}