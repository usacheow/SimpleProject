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

    val openAuthScreen: LiveData<SimpleAction> get() = _openAuthScreenAction
    private val _openAuthScreenAction by lazy { ActionLiveData<SimpleAction>() }

    val openContentScreen: LiveData<SimpleAction> get() = _openContentScreenAction
    private val _openContentScreenAction by lazy { ActionLiveData<SimpleAction>() }

    init {
        _openAuthScreenAction.value = SimpleAction()
    }

    fun onPinCodeEntered() {
        _openContentScreenAction.value = SimpleAction()
    }

    fun onSignIn() {
        _openContentScreenAction.value = SimpleAction()
    }

    fun onSignUp() {
        _openContentScreenAction.value = SimpleAction()
    }

    fun onSignOut() {
        _openAuthScreenAction.value = SimpleAction()
    }
}