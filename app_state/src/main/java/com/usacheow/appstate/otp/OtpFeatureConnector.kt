package com.usacheow.appstate.otp

import androidx.lifecycle.viewModelScope
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpFeatureConnector @Inject constructor() : SimpleViewModel() {

    private val _updateCodeStateAction = Channel<OtpState>()
    val updateCodeStateAction = _updateCodeStateAction.receiveAsFlow()

    private val _updateCodeResultStateAction = Channel<OtpResultState>()
    val updateCodeResultStateAction = _updateCodeResultStateAction.receiveAsFlow()

    fun onResendClicked() = viewModelScope.launch {
        _updateCodeStateAction.send(OtpState.CodeRequested)
    }

    fun onCodeInputted(code: String) = viewModelScope.launch {
        _updateCodeStateAction.send(OtpState.CodeInputted(code))
    }

    fun onCheckSucceed() = viewModelScope.launch {
        _updateCodeResultStateAction.send(OtpResultState.CodeSucceed)
    }

    fun onCheckFailed(message: String?) = viewModelScope.launch {
        message ?: return@launch
        _updateCodeResultStateAction.send(OtpResultState.CodeFailed(message))
    }
}

sealed class OtpState {

    data class CodeInputted(val code: String) : OtpState()

    object CodeRequested : OtpState()
}

sealed class OtpResultState {

    object CodeSucceed : OtpResultState()

    data class CodeFailed(val message: String) : OtpResultState()
}