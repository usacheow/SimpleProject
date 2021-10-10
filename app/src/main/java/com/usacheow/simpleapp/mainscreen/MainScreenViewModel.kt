package com.usacheow.simpleapp.mainscreen

import androidx.lifecycle.viewModelScope
import com.usacheow.core.resource.ResourcesWrapper
import com.usacheow.core.toSource
import com.usacheow.coredata.database.SettingsStorage
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.utils.EventChannel
import com.usacheow.coreui.utils.triggerBy
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.simpleapp.R
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

    private val onBoardingArgs = OnBoardingMediator.OnBoardingArgs(mutableListOf(
        OnBoardingMediator.OnBoardingArgs.Page(
            defaultImageRes = R.drawable.on_boarding_1,
            title = resources.getString(R.string.on_boarding_title_1).toSource(),
            description = resources.getString(R.string.on_boarding_description_1).toSource()),
        OnBoardingMediator.OnBoardingArgs.Page(
            defaultImageRes = R.drawable.on_boarding_2,
            title = resources.getString(R.string.on_boarding_title_2).toSource(),
            description = resources.getString(R.string.on_boarding_description_2).toSource()),
        OnBoardingMediator.OnBoardingArgs.Page(
            defaultImageRes = R.drawable.on_boarding_3,
            title = resources.getString(R.string.on_boarding_title_3).toSource(),
            description = resources.getString(R.string.on_boarding_description_3).toSource())
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
        data class OpenOnBoardingScreen(val args: OnBoardingMediator.OnBoardingArgs) : Action()
        object OpenAuthScreen : Action()
        object OpenPinScreen : Action()
        object OpenAppScreen : Action()
    }
}