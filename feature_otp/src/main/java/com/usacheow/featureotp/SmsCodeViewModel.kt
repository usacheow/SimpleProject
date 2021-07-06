package com.usacheow.featureotp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.appstate.R
import com.usacheow.coreui.resource.ResourcesWrapper
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

private const val EMPTY_CODE = ""
private const val SMS_CODE_TIMEOUT_SECONDS = 60
private const val SECOND_IN_MILLISECONDS = 1000L
private const val CODE_LENGTH_DEFAULT_VALUE = 4

@HiltViewModel
class SmsCodeViewModel @Inject constructor(
    private val resources: ResourcesWrapper,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val defaultResendButtonState = CodeResendButtonState(resources.getString(R.string.sms_code_resend), true)

    private val _isResendButtonState = MutableStateFlow(defaultResendButtonState)
    val isResendButtonState = _isResendButtonState.asStateFlow()

    private val _inputtedCodeState = MutableStateFlow(EMPTY_CODE)
    val inputtedCodeState = _inputtedCodeState.asStateFlow()

    private val _maxCodeLengthState = MutableStateFlow(0)
    val maxCodeLengthState = _maxCodeLengthState.asStateFlow()

    private val _updateCodeStateAction = Channel<SmsCodeState>()
    val updateCodeStateAction = _updateCodeStateAction.receiveAsFlow()

    private var timerJob: Job? = null

    companion object {
        const val CODE_LENGTH_KEY = "codeLength"
    }

    init {
        viewModelScope.launch {
            val codeLength = savedStateHandle.get<Int>(CODE_LENGTH_KEY) ?: CODE_LENGTH_DEFAULT_VALUE
            _maxCodeLengthState.emit(codeLength)
            onDigitAdded("")
            if (timerJob == null) {
                timerJob = startTimer()
            }
        }
    }

    fun onResendClicked() = viewModelScope.launch {
        timerJob = startTimer()
        _updateCodeStateAction.send(SmsCodeState.CodeRequested)
    }

    private fun startTimer() = viewModelScope.launch(Dispatchers.IO) {
        repeat(SMS_CODE_TIMEOUT_SECONDS) {
            val text = resources.getString(R.string.sms_code_resend_timer, SMS_CODE_TIMEOUT_SECONDS - it)
            _isResendButtonState.emit(CodeResendButtonState(text, false))
            delay(SECOND_IN_MILLISECONDS)
        }
        _isResendButtonState.emit(defaultResendButtonState)

        timerJob = null
    }

    fun onDigitAdded(digit: String) {
        if (_inputtedCodeState.value.length >= _maxCodeLengthState.value) return

        _inputtedCodeState.value += digit
        if (_inputtedCodeState.value.length > _maxCodeLengthState.value) {
            _inputtedCodeState.value = _inputtedCodeState.value.substring(_maxCodeLengthState.value)
        }
        if (_inputtedCodeState.value.length == _maxCodeLengthState.value) {
            onCodeInputted()
        }
    }

    fun onDigitRemoved() {
        if (_inputtedCodeState.value.isEmpty()) {
            return
        }

        _inputtedCodeState.value = _inputtedCodeState.value.dropLast(1)
    }

    private fun onCodeInputted() = viewModelScope.launch {
        _updateCodeStateAction.send(SmsCodeState.CodeInputted(_inputtedCodeState.value))
    }
}

data class CodeResendButtonState(
    val text: String,
    val isEnable: Boolean,
)

sealed class SmsCodeState {

    data class CodeInputted(val code: String) : SmsCodeState()

    object CodeRequested : SmsCodeState()
}