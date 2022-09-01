package com.usacheow.basesources

import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.container.textValue
import com.usacheow.corecommon.model.makeUserReadableErrorMessage
import com.usacheow.corecommon.strings.StringKey
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private val NotificationVisibilitySeconds = 5.seconds

interface NotificationsSource {

    val state: StateFlow<State?>

    suspend fun sendMessage(value: TextValue)

    suspend fun sendWarning(value: TextValue)

    suspend fun sendError(value: TextValue)

    suspend fun sendError(exception: Exception)

    suspend fun hide()

    sealed class State(val value: TextValue) {
        class Message(value: TextValue) : State(value)
        class Warning(value: TextValue) : State(value)
        class Error(value: TextValue) : State(value)
    }
}

class NotificationsSourceImpl @Inject constructor(
    networkStateProvider: NetworkStateSource,
    @ApplicationCoroutineScope private val coroutineScope: CoroutineScope,
) : NotificationsSource {

    private val _state = MutableStateFlow<NotificationsSource.State?>(null)
    override val state = _state.asStateFlow()

    private var needShowInternetConnectedMessage = false
    private var hideMessageJob: Job? = null

    init {
        networkStateProvider.state.onEach {
            val state = when (it) {
                NetworkStateSource.State.Available -> {
                    NotificationsSource.State.Message(StringKey.ConnectionSuccessMessage.textValue())
                }
                NetworkStateSource.State.Unavailable -> {
                    NotificationsSource.State.Error(StringKey.ConnectionErrorMessage.textValue())
                }
            }
            if (it == NetworkStateSource.State.Unavailable) {
                needShowInternetConnectedMessage = true
            }
            if (it == NetworkStateSource.State.Unavailable || needShowInternetConnectedMessage) {
                _state.emit(state)
            }
            restartTimer()
        }.launchIn(coroutineScope)
    }

    override suspend fun sendMessage(value: TextValue) {
        _state.emit(NotificationsSource.State.Message(value))
        restartTimer()
    }

    override suspend fun sendWarning(value: TextValue) {
        _state.emit(NotificationsSource.State.Warning(value))
        restartTimer()
    }

    override suspend fun sendError(value: TextValue) {
        _state.emit(NotificationsSource.State.Error(value))
        restartTimer()
    }

    override suspend fun sendError(exception: Exception) {
        _state.emit(NotificationsSource.State.Error(exception.makeUserReadableErrorMessage()))
        restartTimer()
    }

    override suspend fun hide() {
        _state.emit(null)
    }

    private fun restartTimer() {
        hideMessageJob?.cancel()
        hideMessageJob = coroutineScope.launch {
            delay(NotificationVisibilitySeconds)
            _state.emit(null)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface NotificationsSourceModule {

    @Binds
    @Singleton
    fun notificationsSource(source: NotificationsSourceImpl): NotificationsSource
}