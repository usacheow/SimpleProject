package com.usacheow.coredata.source

import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.makeUserReadableErrorMessage
import com.usacheow.coredata.coroutine.ApplicationCoroutineScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.usacheow.corecommon.R as CoreCommonR

interface NotificationsSource {

    val state: SharedFlow<State?>

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
    @ApplicationCoroutineScope coroutineScope: CoroutineScope,
    networkStateProvider: NetworkStateSource,
) : NotificationsSource {

    private val _state = MutableSharedFlow<NotificationsSource.State?>()
    override val state = _state.asSharedFlow()

    init {
        networkStateProvider.state.onEach {
            val res = when (it) {
                NetworkStateSource.State.Available -> CoreCommonR.string.connection_success_message
                NetworkStateSource.State.Unavailable -> CoreCommonR.string.connection_error_message
            }
            _state.emit(NotificationsSource.State.Message(TextValue.Res(res)))
        }.launchIn(coroutineScope)
    }

    override suspend fun sendMessage(value: TextValue) {
        _state.emit(NotificationsSource.State.Message(value))
    }

    override suspend fun sendWarning(value: TextValue) {
        _state.emit(NotificationsSource.State.Warning(value))
    }

    override suspend fun sendError(value: TextValue) {
        _state.emit(NotificationsSource.State.Error(value))
    }

    override suspend fun sendError(exception: Exception) {
        _state.emit(NotificationsSource.State.Error(exception.makeUserReadableErrorMessage()))
    }

    override suspend fun hide() {
        _state.emit(null)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface NotificationsSourceModule {

    @Binds
    @Singleton
    fun notificationsSource(source: NotificationsSourceImpl): NotificationsSource
}