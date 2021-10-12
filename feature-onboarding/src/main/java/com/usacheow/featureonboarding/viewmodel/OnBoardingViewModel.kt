package com.usacheow.featureonboarding.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.core.ImageSource
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.core.toImageSource
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.utils.navigation.requireArgs
import com.usacheow.coreui.utils.navigation.requireNextScreenDirection
import com.usacheow.coreui.viewmodel.triggerBy
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featureonboarding.view.OnBoardingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val storage: SettingsStorage,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }
    private val pages by lazy { savedStateHandle.requireArgs<OnBoardingMediator.OnBoardingArgs>().pages }

    private val _openAppScreenAction = EventChannel<FeatureNavDirection>()
    val openAppScreenAction = _openAppScreenAction.receiveAsFlow()

    private val _pagesState = MutableStateFlow(pages.map(::mapToItem))
    val pagesState = _pagesState.asStateFlow()

    fun onOnBoardingFinished() = viewModelScope.launch {
        storage.isFirstEntry = false
        _openAppScreenAction triggerBy nextScreenDirection
    }

    fun onOnBoardingSkipped() = viewModelScope.launch {
        storage.isFirstEntry = false
        _openAppScreenAction triggerBy nextScreenDirection
    }

    private fun mapToItem(item: OnBoardingMediator.OnBoardingArgs.Page) = OnBoardingItem(
        image = item.imageUrl?.toImageSource()
            ?: item.defaultImageRes?.toImageSource()
            ?: ImageSource.Empty,
        title = item.title,
        description = item.description,
    )
}