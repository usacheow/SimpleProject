package com.usacheow.baseotp

import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.trigger
import com.usacheow.coreui.utils.triggerBy
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpFeatureConnector @Inject constructor() : SimpleViewModel() {

    private val _updateCodeStateAction = EventChannel<OtpCodeState>()
    val updateCodeStateAction = _updateCodeStateAction.receiveAsFlow()

    private val _showErrorStateAction = EventChannel<TextSource>()
    val showErrorMessageAction = _showErrorStateAction.receiveAsFlow()

    private val _closeDialogAction = EventChannel<SimpleAction>()
    val closeDialogAction = _closeDialogAction.receiveAsFlow()

    fun onResendClicked() = viewModelScope.launch {
        _updateCodeStateAction triggerBy OtpCodeState.OtpCodeRequested
    }

    fun onCodeInputted(code: String) = viewModelScope.launch {
        _updateCodeStateAction triggerBy OtpCodeState.OtpCodeInputted(code)
    }

    fun notifyAboutSuccess() = viewModelScope.launch {
        _closeDialogAction.trigger()
    }

    fun notifyAboutError(message: TextSource?) = viewModelScope.launch {
        message ?: return@launch
        _showErrorStateAction triggerBy message
    }
}

sealed class OtpCodeState {

    data class OtpCodeInputted(val code: String) : OtpCodeState()

    object OtpCodeRequested : OtpCodeState()
}