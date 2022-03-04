package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.coredata.network.getMessage
import com.usacheow.corenavigation.base.requireNextScreenDirection
import com.usacheow.coreui.viewmodel.EventChannel
import com.usacheow.coreui.viewmodel.SimpleViewModel
import com.usacheow.coreui.viewmodel.triggerBy
import com.usacheow.coreui.viewmodel.tryPublish
import com.usacheow.coreuiview.helper.get
import com.usacheow.coreuiview.resourcewrapper.ResourcesWrapper
import com.usacheow.featureauth.domain.AuthInteractor
import com.usacheow.featureauth.presentation.isPhoneNumberValid
import com.usacheow.featureauth.presentation.normalizedPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignInWithPhoneViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
    private val savedStateHandle: SavedStateHandle,
) : SimpleViewModel() {

    private val _isSubmitButtonEnabledState = MutableStateFlow(false)
    val isSubmitButtonEnabledState = _isSubmitButtonEnabledState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(false)
    val isLoadingState = _isLoadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    private val _openNextScreenAction = EventChannel<FeatureNavDirection>()
    val openNextScreenAction = _openNextScreenAction.receiveAsFlow()

    private val _openSignUpScreenAction = EventChannel<FeatureNavDirection>()
    val openSignUpScreenAction = _openSignUpScreenAction.receiveAsFlow()

    private val nextScreenDirection by lazy { savedStateHandle.requireNextScreenDirection() }

    private var phoneNumber = ""

    fun onPhoneChanged(phone: String) {
        _isSubmitButtonEnabledState.value = phone.isPhoneNumberValid()
    }

    fun onSubmitClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    fun onSignInClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    private fun sendPhoneNumberIfValid(phone: String) = viewModelScope.launch {
        phoneNumber = phone.normalizedPhoneNumber()
        if (!phoneNumber.isPhoneNumberValid()) {
            return@launch
        }

        _isLoadingState tryPublish true

        interactor.signInWithPhone(phone).doOnSuccess {
            _openNextScreenAction triggerBy nextScreenDirection
        }.doOnError { exception, _ ->
            _errorState tryPublish exception.getMessage().get(resources).toString()
        }

        _isLoadingState tryPublish false
    }

    fun onSignUpClicked() = viewModelScope.launch {
        _openSignUpScreenAction triggerBy nextScreenDirection
    }
}