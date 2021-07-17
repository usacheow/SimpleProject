package com.usacheow.appstate

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppStateViewModel @Inject constructor(
    private val storage: SettingsStorage,
) : SimpleViewModel() {

    private val _openOnBoardingScreenAction = Channel<SimpleAction>()
    val openOnBoardingScreenAction = _openOnBoardingScreenAction.receiveAsFlow()

    private val _openAuthScreenAction = Channel<SimpleAction>()
    val openAuthScreenAction = _openAuthScreenAction.receiveAsFlow()

    private val _openPinScreenAction = Channel<SimpleAction>()
    val openPinScreenAction = _openPinScreenAction.receiveAsFlow()

    private val _openAppScreenAction = Channel<SimpleAction>()
    val openAppScreenAction = _openAppScreenAction.receiveAsFlow()

    init {
        viewModelScope.launch {
            when {
                storage.isFirstEntry -> _openOnBoardingScreenAction.send(SimpleAction)
                else -> _openAppScreenAction.send(SimpleAction)
            }
        }
    }

    fun onOnBoardingFinished() = viewModelScope.launch {
        storage.isFirstEntry = false
        _openAppScreenAction.send(SimpleAction)
    }

    fun onPinCodeEntered() = viewModelScope.launch {
        _openAppScreenAction.send(SimpleAction)
    }

    fun onSignedIn() = viewModelScope.launch {
        _openAppScreenAction.send(SimpleAction)
    }

    fun onSignedUp() = viewModelScope.launch {
        _openAppScreenAction.send(SimpleAction)
    }

    fun onSignedOut() = viewModelScope.launch {
        _openAuthScreenAction.send(SimpleAction)
    }
}