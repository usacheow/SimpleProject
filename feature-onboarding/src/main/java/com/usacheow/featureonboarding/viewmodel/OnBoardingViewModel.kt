package com.usacheow.featureonboarding.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coreui.utils.navigation.requireNextScreenDirection
import com.usacheow.coreui.utils.triggerBy
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.featureonboarding.R
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

    private val _openAppScreenAction = EventChannel<FeatureNavDirection>()
    val openAppScreenAction = _openAppScreenAction.receiveAsFlow()

    private val _pagesState = MutableStateFlow(onBoardingData)
    val pagesState = _pagesState.asStateFlow()

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }

    private val onBoardingData get() = mutableListOf(
        OnBoardingItem(R.drawable.on_boarding_1, R.string.on_boarding_title_1, R.string.on_boarding_description_1),
        OnBoardingItem(R.drawable.on_boarding_2, R.string.on_boarding_title_2, R.string.on_boarding_description_2),
        OnBoardingItem(R.drawable.on_boarding_3, R.string.on_boarding_title_3, R.string.on_boarding_description_3)
    )

    fun onOnBoardingFinished() = viewModelScope.launch {
        storage.isFirstEntry = false
        _openAppScreenAction triggerBy nextScreenDirection
    }

    fun onOnBoardingSkipped() = viewModelScope.launch {
        storage.isFirstEntry = false
        _openAppScreenAction triggerBy nextScreenDirection
    }
}