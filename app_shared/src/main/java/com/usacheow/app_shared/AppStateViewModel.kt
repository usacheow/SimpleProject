package com.usacheow.app_shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.database.Storage
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction
import javax.inject.Inject

class AppStateViewModel
@Inject constructor(
    private val storage: Storage
) : ViewModel() {

    val openOnBoardingScreen: LiveData<SimpleAction> get() = _openOnBoardingScreenAction
    private val _openOnBoardingScreenAction by lazy { ActionLiveData<SimpleAction>() }

    val openAuthScreen: LiveData<SimpleAction> get() = _openAuthScreenAction
    private val _openAuthScreenAction by lazy { ActionLiveData<SimpleAction>() }

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