package com.usacheow.appstate.otp

import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpFeatureConnector @Inject constructor() : SimpleViewModel() {

    private val _updateCodeStateAction = Channel<OtpCodeState>()
    val updateCodeStateAction = _updateCodeStateAction.receiveAsFlow()

    private val _showErrorStateAction = Channel<TextSource>()
    val showErrorMessageAction = _showErrorStateAction.receiveAsFlow()

    private val _closeDialogAction = Channel<SimpleAction>()
    val closeDialogAction = _closeDialogAction.receiveAsFlow()

    fun onResendClicked() = viewModelScope.launch {
        _updateCodeStateAction.send(OtpCodeState.OtpCodeRequested)
    }

    fun onCodeInputted(code: String) = viewModelScope.launch {
        _updateCodeStateAction.send(OtpCodeState.OtpCodeInputted(code))
    }

    fun notifyAboutSuccess() = viewModelScope.launch {
        _closeDialogAction.send(SimpleAction)
    }

    fun notifyAboutError(message: TextSource?) = viewModelScope.launch {
        message ?: return@launch
        _showErrorStateAction.send(message)
    }
}

sealed class OtpCodeState {

    data class OtpCodeInputted(val code: String) : OtpCodeState()

    object OtpCodeRequested : OtpCodeState()
}