package com.usacheow.showcaseutils.notification

import com.usacheow.coreuicompose.uikit.status.FullScreenMessage
import com.usacheow.showcaseutils.featuretoggle.EditableFeatureToggle
import com.usacheow.showcaseutils.featuretoggle.FeatureToggle
import com.usacheow.showcaseutils.featuretoggle.FeatureToggleImpl
import com.usacheow.showcaseutils.featuretoggle.FeatureToggleUpdater
import com.usacheow.showcaseutils.featuretoggle.LocalFeatureToggleStorage
import com.usacheow.showcaseutils.featuretoggle.RemoteFeatureToggleStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import javax.inject.Inject

interface FullScreenMessageDelegate {

    val fullScreenMessageState: StateFlow<FullScreenMessage?>

    suspend fun showFullScreenMessage(state: FullScreenMessage)

    suspend fun hideFullScreenMessage()
}

class FullScreenMessageDelegateImpl : FullScreenMessageDelegate {

    private val _fullScreenMessageState = MutableStateFlow<FullScreenMessage?>(null)
    override val fullScreenMessageState = _fullScreenMessageState.asStateFlow()

    override suspend fun showFullScreenMessage(state: FullScreenMessage) {
        _fullScreenMessageState.emit(state)
    }

    override suspend fun hideFullScreenMessage() {
        _fullScreenMessageState.emit(null)
    }
}

val fullScreenMessageDiModule by DI.Module {
    bindSingleton<FullScreenMessageDelegate> { FullScreenMessageDelegateImpl() }
}