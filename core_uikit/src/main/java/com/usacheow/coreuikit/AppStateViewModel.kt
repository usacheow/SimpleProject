package com.usacheow.coreuikit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.usacheow.coredata.database.Storage
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import com.usacheow.coreuikit.viewmodels.livedata.SimpleAction
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

    val openDemoScreen: LiveData<SimpleAction> get() = _openDemoScreenAction
    private val _openDemoScreenAction by lazy { ActionLiveData<SimpleAction>() }

    init {
        _openDemoScreenAction.value = SimpleAction()
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