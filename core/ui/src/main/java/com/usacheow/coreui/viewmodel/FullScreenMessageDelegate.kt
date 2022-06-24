package com.usacheow.coreui.viewmodel

import com.usacheow.coreuicompose.uikit.status.FullScreenMessage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface FullScreenMessageDelegate {

    val fullScreenMessageState: StateFlow<FullScreenMessage?>

    suspend fun showFullScreenMessage(state: FullScreenMessage)

    suspend fun hideFullScreenMessage()
}

class FullScreenMessageDelegateImpl @Inject constructor() : FullScreenMessageDelegate {

    private val _fullScreenMessageState = MutableStateFlow<FullScreenMessage?>(null)
    override val fullScreenMessageState = _fullScreenMessageState.asStateFlow()

    override suspend fun showFullScreenMessage(state: FullScreenMessage) {
        _fullScreenMessageState.emit(state)
    }

    override suspend fun hideFullScreenMessage() {
        _fullScreenMessageState.emit(null)
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface FullScreenMessageDelegateModule {

    @Binds
    @Singleton
    fun fullScreenMessageDelegate(source: FullScreenMessageDelegateImpl): FullScreenMessageDelegate
}