package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.core.toTextSource
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coremediator.OnBoardingFeatureProvider
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.triggerBy
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.simpleapp.R as AppR
import com.usacheow.coreui.R as CoreUiR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    resources: ResourcesWrapper,
    private val storage: SettingsStorage,
) : SimpleViewModel() {

    private val _action = EventChannel<Action>()
    val action = _action.receiveAsFlow()

    private val onBoardingArgs = OnBoardingFeatureProvider.OnBoardingArgs(mutableListOf(
        OnBoardingFeatureProvider.OnBoardingArgs.Page(
            defaultImageRes = CoreUiR.drawable.ic_user,
            title = resources.getString(AppR.string.on_boarding_title_1).toTextSource(),
            description = resources.getString(AppR.string.on_boarding_description_1).toTextSource()),
        OnBoardingFeatureProvider.OnBoardingArgs.Page(
            defaultImageRes = CoreUiR.drawable.ic_user,
            title = resources.getString(AppR.string.on_boarding_title_2).toTextSource(),
            description = resources.getString(AppR.string.on_boarding_description_2).toTextSource()),
        OnBoardingFeatureProvider.OnBoardingArgs.Page(
            defaultImageRes = CoreUiR.drawable.ic_user,
            title = resources.getString(AppR.string.on_boarding_title_3).toTextSource(),
            description = resources.getString(AppR.string.on_boarding_description_3).toTextSource())
    ))

    init {
        viewModelScope.launch {
            _action triggerBy when {
                storage.isFirstEntry -> Action.OpenOnBoardingScreen(onBoardingArgs)
                else -> Action.OpenAppScreen
            }
        }
    }

    sealed class Action {
        data class OpenOnBoardingScreen(val args: OnBoardingFeatureProvider.OnBoardingArgs) : Action()
        object OpenAuthScreen : Action()
        object OpenPinScreen : Action()
        object OpenAppScreen : Action()
    }
}