package com.usacheow.appshared

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.database.Storage
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction

class AppStateViewModel
@ViewModelInject constructor(
    private val storage: Storage,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val openOnBoardingScreen: LiveData<SimpleAction> get() = _openOnBoardingScreenAction
    private val _openOnBoardingScreenAction by lazy { ActionLiveData<SimpleAction>() }

    val openAuthScreen: LiveData<SimpleAction> get() = _openAuthScreenAction
    private val _openAuthScreenAction by lazy { ActionLiveData<SimpleAction>() }

    val openPinScreen: LiveData<SimpleAction> get() = _openPinScreenAction
    private val _openPinScreenAction by lazy { ActionLiveData<SimpleAction>() }

    val openAppScreen: LiveData<SimpleAction> get() = _openAppScreenAction
    private val _openAppScreenAction by lazy { ActionLiveData<SimpleAction>() }

    init {
        _openAppScreenAction.value = SimpleAction()
    }

    fun onPinCodeEntered() {
        _openAppScreenAction.value = SimpleAction()
    }

    fun onSignIn() {
        _openAppScreenAction.value = SimpleAction()
    }

    fun onSignUp() {
        _openAppScreenAction.value = SimpleAction()
    }

    fun onSignOut() {
        _openAuthScreenAction.value = SimpleAction()
    }

    fun onOnBoardingFinished() {
        storage.isFirstEntry = false
        _openAppScreenAction.value = SimpleAction()
    }
}