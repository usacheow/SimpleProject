package com.usacheow.appshared.otp

import androidx.lifecycle.viewModelScope
import com.usacheow.appshared.R
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.utils.SimpleAction
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EMPTY_MESSAGE = ""
private const val EMPTY_CODE = ""
private const val SMS_CODE_TIMEOUT_SECONDS = 60
private const val SECOND_IN_MILLISECONDS = 1000L

@HiltViewModel
class SmsCodeViewModel @Inject constructor(
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    private val _inputtedCodeState = MutableStateFlow(EMPTY_CODE)
    internal val inputtedCodeState = _inputtedCodeState.asStateFlow()

    private val _maxCodeLengthState = MutableStateFlow(0)
    internal val maxCodeLengthState = _maxCodeLengthState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    internal val isLoadingState = _isLoadingState.asStateFlow()

    private val _resendButtonTextState = MutableStateFlow(resources.getString(R.string.sms_code_resend))
    internal val resendButtonTextState = _resendButtonTextState.asStateFlow()

    private val _isResendButtonEnabledAction = MutableStateFlow(true)
    internal val isResendButtonEnabledAction = _isResendButtonEnabledAction.asStateFlow()

    private val _processCodeAction = Channel<String>()
    val processCodeAction = _processCodeAction.receiveAsFlow()

    private val _closeScreenAction = Channel<SimpleAction>()
    internal val closeScreenAction = _closeScreenAction.receiveAsFlow()

    private val _showMessageAction = Channel<String>()
    internal val showMessageAction = _showMessageAction.receiveAsFlow()

    private var timerJob: Job? = null

    internal fun onResendClicked() {
        timerJob = startTimer()
    }

    private fun startTimer() = viewModelScope.launch(Dispatchers.IO) {
        _isResendButtonEnabledAction.emit(false)

        repeat(SMS_CODE_TIMEOUT_SECONDS) {
            val text = resources.getString(R.string.sms_code_resend_timer, SMS_CODE_TIMEOUT_SECONDS - it)
            _resendButtonTextState.emit(text)
            delay(SECOND_IN_MILLISECONDS)
        }
        _resendButtonTextState.emit(resources.getString(R.string.sms_code_resend))

        timerJob = null
        _isResendButtonEnabledAction.emit(true)
    }

    internal fun setupInitState(codeLength: Int) = viewModelScope.launch {
        _maxCodeLengthState.emit(codeLength)
        onDigitAdded("")
        if (timerJob == null) {
            timerJob = startTimer()
        }
    }

    internal fun onDigitAdded(digit: String) {
        if (_inputtedCodeState.value.length >= _maxCodeLengthState.value) return

        _inputtedCodeState.value += digit
        if (_inputtedCodeState.value.length > _maxCodeLengthState.value) {
            _inputtedCodeState.value = _inputtedCodeState.value.substring(_maxCodeLengthState.value)
        }
        if (_inputtedCodeState.value.length == _maxCodeLengthState.value) {
            onCodeInputted()
        }
    }

    internal fun onDigitRemoved() {
        if (_inputtedCodeState.value.isEmpty()) {
            return
        }

        _inputtedCodeState.value = _inputtedCodeState.value.dropLast(1)
    }

    private fun onCodeInputted() = viewModelScope.launch {
        _showMessageAction.send(EMPTY_MESSAGE)
        _isLoadingState.emit(true)
        _processCodeAction.send(_inputtedCodeState.value)
    }

    fun showMessage(message: String) = viewModelScope.launch {
        _isLoadingState.emit(false)
        _showMessageAction.send(message)
    }

    fun closeScreen() = viewModelScope.launch {
        _closeScreenAction.send(SimpleAction)
    }

    internal fun onDismissed() = viewModelScope.launch {
        _maxCodeLengthState.emit(0)
        _inputtedCodeState.value = EMPTY_CODE
        _isLoadingState.value = false
    }
}