package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.utils.trigger
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val storage: SettingsStorage,
) : SimpleViewModel() {

    private val _openOnBoardingScreenAction = EventChannel<SimpleAction>()
    val openOnBoardingScreenAction = _openOnBoardingScreenAction.receiveAsFlow()

    private val _openAuthScreenAction = EventChannel<SimpleAction>()
    val openAuthScreenAction = _openAuthScreenAction.receiveAsFlow()

    private val _openPinScreenAction = EventChannel<SimpleAction>()
    val openPinScreenAction = _openPinScreenAction.receiveAsFlow()

    private val _openAppScreenAction = EventChannel<SimpleAction>()
    val openAppScreenAction = _openAppScreenAction.receiveAsFlow()

    init {
        viewModelScope.launch {
            when {
                storage.isFirstEntry -> _openOnBoardingScreenAction.trigger()
                else -> _openAppScreenAction.trigger()
            }
        }
    }
}