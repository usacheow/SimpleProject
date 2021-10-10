package com.usacheow.baseotp

import androidx.lifecycle.viewModelScope
import com.usacheow.core.TextSource
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.triggerBy
import com.usacheow.coreui.viewmodel.SimpleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpFeatureConnector @Inject constructor() : SimpleViewModel() {

    private val _featureEvent = EventChannel<Event>()
    val featureEvent = _featureEvent.receiveAsFlow()

    private val _featureEffect = EventChannel<Effect>()
    val featureEffect = _featureEffect.receiveAsFlow()

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

/*
class FeatureConnector<EVENT, EFFECT> : ViewModel() {

    private val _featureEvent = Channel<EVENT>()
    val featureEvent = _featureEvent.receiveAsFlow()

    private val _featureEffect = Channel<EFFECT>()
    val featureEffect = _featureEffect.receiveAsFlow()
}*/
