package com.usacheow.featureonboarding.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.corecommon.resource.ImageSource
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.corecommon.resource.toImageSource
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.corenavigation.base.requireArgs
import com.usacheow.corenavigation.base.requireNextScreenDirection
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.triggerBy
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
    private val pages by lazy { savedStateHandle.requireArgs<OnBoardingFeatureProvider.OnBoardingArgs>().pages }

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

    private fun mapToItem(item: OnBoardingFeatureProvider.OnBoardingArgs.Page) = OnBoardingItem(
        image = item.imageUrl?.toImageSource()
            ?: item.defaultImageRes?.toImageSource()
            ?: ImageSource.Empty,
        title = item.title,
        description = item.description,
    )
}