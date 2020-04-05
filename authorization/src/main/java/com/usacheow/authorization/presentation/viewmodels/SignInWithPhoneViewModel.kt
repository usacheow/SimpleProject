package com.usacheow.authorization.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usacheow.authorization.domain.AuthInteractor
import com.usacheow.coredata.CONFIRM_CODE_LENGTH
import com.usacheow.coredata.network.error.ErrorProcessorImpl
import com.usacheow.coredata.network.observer.SimpleCompletableObserver
import com.usacheow.coreuikit.utils.ext.normalizedPhoneNumber
import com.usacheow.coreuikit.viewmodels.NetworkRxViewModel
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import com.usacheow.coreuikit.viewmodels.livedata.SimpleAction
import javax.inject.Inject

private const val EXPECTED_PHONE_NUMBER_LENGTH = 10

class SignInWithPhoneViewModel
@Inject constructor(
    errorProcessor: ErrorProcessorImpl,
    private val interactor: AuthInteractor
) : NetworkRxViewModel(errorProcessor) {

    val codeConfirmState: LiveData<String?> get() = _codeConfirmStateLiveData
    private val _codeConfirmStateLiveData by lazy { MutableLiveData<String?>() }

    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonEnabledLiveData
    private val _submitButtonEnabledLiveData by lazy { MutableLiveData<Boolean>() }

    val openMainScreen: LiveData<SimpleAction> get() = _openMainScreenLiveData
    private val _openMainScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openSignUpScreen: LiveData<SimpleAction> get() = _openSignUpScreenLiveData
    private val _openSignUpScreenLiveData by lazy { ActionLiveData<SimpleAction>() }

    val openConfirmScreen: LiveData<Int> get() = _openConfirmScreenLiveData
    private val _openConfirmScreenLiveData by lazy { ActionLiveData<Int>() }

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

        val observer = SimpleCompletableObserver.Builder()
            .onSubscribe { _isLoadingStateLiveData.postValue(true) }
            .onError(::onError)
            .onSuccess {
                _isLoadingStateLiveData.value = false
                _openConfirmScreenLiveData.value = CONFIRM_CODE_LENGTH
            }
            .build()

        disposables.clear()
        interactor.signInWithPhone(phone, observer)
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }

    fun onCodeInputted(code: String) {
        if (code.isEmpty()) return

        val observer = SimpleCompletableObserver.Builder()
            .onError(::onError)
            .onSuccess {
                _codeConfirmStateLiveData.value = null
                _openMainScreenLiveData.value = SimpleAction()
//                _codeConfirmStateLiveData.value = "Неверный код"
            }.build()
        interactor.verifyPhone(phoneNumber, code, observer)
    }

    override fun onCleared() {
        interactor.onDetach()
        super.onCleared()
    }
}