package com.usacheow.baseotp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.viewmodel.triggerBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpFeatureConnector @Inject constructor() :
    FeatureConnector<OtpFeatureConnector.Event, OtpFeatureConnector.Effect>() {

    fun onResendClicked() = viewModelScope.launch {
        _featureEvent triggerBy Event.CodeRequested
    }

    fun onCodeInputted(code: String) = viewModelScope.launch {
        _featureEvent triggerBy Event.CodeInputted(code)
    }

    fun notifyAboutSuccess() = viewModelScope.launch {
        _featureEffect triggerBy Effect.Success
    }

    fun notifyAboutError(message: TextSource?) = viewModelScope.launch {
        message ?: return@launch
        _featureEffect triggerBy Effect.Error(message)
    }

    sealed class Event {
        object CodeRequested : Event()
        data class CodeInputted(val code: String) : Event()
    }

    sealed class Effect {
        object Success : Effect()
        data class Error(val message: TextSource) : Effect()
    }
}

abstract class FeatureConnector<EVENT, EFFECT> : ViewModel() {

    protected val _featureEvent = Channel<EVENT>()
    val featureEvent = _featureEvent.receiveAsFlow()

    protected val _featureEffect = Channel<EFFECT>()
    val featureEffect = _featureEffect.receiveAsFlow()
}