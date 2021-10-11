package com.usacheow.featureotp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.coremediator.OtpMediator
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.navigation.getArgs
import com.usacheow.coreui.utils.navigation.requireArgs
import com.usacheow.coreui.utils.navigation.requireNextScreenDirection
import com.usacheow.coreui.utils.triggerBy
import com.usacheow.coreui.utils.tryPublish
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.usacheow.featureotp.R as FeatureR

private const val EMPTY_CODE = ""
private const val SMS_CODE_TIMEOUT_SECONDS = 6
private const val SECOND_IN_MILLISECONDS = 1000L
private const val CODE_LENGTH_DEFAULT_VALUE = 4

@HiltViewModel
class SmsCodeViewModel @Inject constructor(
    private val resources: ResourcesWrapper,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val defaultResendButtonState = CodeResendButtonState(
        resources.getString(FeatureR.string.sms_code_resend),
        true,
    )

    private val _isResendButtonState = MutableStateFlow(defaultResendButtonState)
    val isResendButtonState = _isResendButtonState.asStateFlow()

    private val _inputtedCodeState = MutableStateFlow(EMPTY_CODE)
    val inputtedCodeState = _inputtedCodeState.asStateFlow()

    private val _maxCodeLengthState = MutableStateFlow(0)
    val maxCodeLengthState = _maxCodeLengthState.asStateFlow()

    private val _updateCodeStateAction = EventChannel<SmsCodeState>()
    val updateCodeStateAction = _updateCodeStateAction.receiveAsFlow()

    private var timerJob: Job? = null

    private val codeLength by lazy {
        savedStateHandle.getArgs<OtpMediator.OtpArgs>()?.codeLength ?: CODE_LENGTH_DEFAULT_VALUE
    }

    init {
        viewModelScope.launch {
            _maxCodeLengthState tryPublish codeLength
            onDigitAdded("")
            if (timerJob == null) {
                timerJob = startTimer()
            }
        }
    }

    fun onResendClicked() = viewModelScope.launch {
        timerJob = startTimer()
        _updateCodeStateAction triggerBy SmsCodeState.CodeRequested
    }

    private fun startTimer() = viewModelScope.launch(Dispatchers.IO) {
        repeat(SMS_CODE_TIMEOUT_SECONDS) {
            val text = resources.getString(FeatureR.string.sms_code_resend_timer, SMS_CODE_TIMEOUT_SECONDS - it)
            _isResendButtonState tryPublish CodeResendButtonState(text, false)
            delay(SECOND_IN_MILLISECONDS)
        }
        _isResendButtonState tryPublish defaultResendButtonState

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
        _updateCodeStateAction triggerBy SmsCodeState.CodeInputted(_inputtedCodeState.value)
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