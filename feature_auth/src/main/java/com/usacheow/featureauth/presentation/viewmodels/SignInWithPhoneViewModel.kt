package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.usacheow.coredata.network.ifError
import com.usacheow.coredata.network.ifSuccess
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction
import com.usacheow.coreui.livedata.postValue
import com.usacheow.coreui.resources.ResourcesWrapper
import com.usacheow.coreui.utils.values.normalizedPhoneNumber
import com.usacheow.coreui.viewmodels.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EXPECTED_PHONE_NUMBER_LENGTH = 10
private const val CONFIRM_CODE_LENGTH = 4

@HiltViewModel
class SignInWithPhoneViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val resources: ResourcesWrapper,
) : SimpleViewModel() {

    val codeConfirmMessage: LiveData<String> get() = _codeConfirmMessageLiveData
    private val _codeConfirmMessageLiveData by lazy { MutableLiveData<String>() }

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val closeScreen: LiveData<SimpleAction> get() = _closeScreenLiveData
    private val _closeScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openSignUpScreen: LiveData<SimpleAction> get() = _openSignUpScreenLiveData
    private val _openSignUpScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openConfirmScreen: LiveData<Int> get() = _openConfirmScreenLiveData
    private val _openConfirmScreenLiveData by lazy { ActionLiveData<Int>() }

    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData
    private val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }

    val errorState: LiveData<String?> get() = _errorStateLiveData
    private val _errorStateLiveData by lazy { MutableLiveData<String?>() }

    private var phoneNumber = ""

    init {
        _isLoadingStateLiveData.value = false
        _submitButtonEnabledLiveData.value = false
    }

    fun onPhoneChanged(phone: String) {
        _submitButtonEnabledLiveData.value = isValidPhoneNumber(phone.normalizedPhoneNumber())
    }

    private fun isValidPhoneNumber(phone: String) = phone.length == EXPECTED_PHONE_NUMBER_LENGTH

    fun onSubmitClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    fun onSignInClicked(phone: String) {
        sendPhoneNumberIfValid(phone)
    }

    private fun sendPhoneNumberIfValid(phone: String) {
        phoneNumber = phone.normalizedPhoneNumber()
        if (!isValidPhoneNumber(phoneNumber)) return

        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingStateLiveData.postValue = true
            interactor.signInWithPhone(phone).ifSuccess {
                _openConfirmScreenLiveData.postValue = CONFIRM_CODE_LENGTH
            }.ifError {
                _errorStateLiveData.postValue = exception.getMessage(resources.get)
            }
            _isLoadingStateLiveData.postValue = false
        }
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }

    fun onCodeInputted(code: String) {
        if (code.isEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            _isLoadingStateLiveData.postValue = true
            interactor.verifyPhone(phoneNumber, code).ifSuccess {
                _closeScreenLiveData.postValue = SimpleAction()
            }.ifError {
                _codeConfirmMessageLiveData.postValue = "Неверный код"
            }
            _isLoadingStateLiveData.postValue = false
        }
    }
}