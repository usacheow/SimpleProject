package com.usacheow.featureauth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usacheow.coredata.network.error.ErrorProcessorImpl
import com.usacheow.coredata.network.error.MappedException
import com.usacheow.coredata.network.observer.SimpleCompletableObserver
import com.usacheow.coredata.network.setRequestThreads
import com.usacheow.coreui.livedata.ActionLiveData
import com.usacheow.coreui.livedata.SimpleAction
import com.usacheow.coreui.utils.ext.normalizedPhoneNumber
import com.usacheow.coreui.viewmodels.SimpleViewModel
import com.usacheow.featureauth.domain.AuthInteractor
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

private const val EXPECTED_PHONE_NUMBER_LENGTH = 10
private const val CONFIRM_CODE_LENGTH = 4

class SignInWithPhoneViewModel
@Inject constructor(
    private val errorProcessor: ErrorProcessorImpl,
    private val interactor: AuthInteractor
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
    protected val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }

    val errorState: LiveData<MappedException> get() = _errorStateLiveData
    protected val _errorStateLiveData by lazy { MutableLiveData<MappedException>() }

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
            .onSubscribe {
                _isLoadingStateLiveData.postValue(true)
                disposables += it
            }
            .onSuccess {
                _isLoadingStateLiveData.value = false
                _openConfirmScreenLiveData.value = CONFIRM_CODE_LENGTH
            }
            .onError {
                _errorStateLiveData.value = errorProcessor.process(it)
            }
            .build()

        disposables.clear()
        interactor.signInWithPhone(phone)
            .setRequestThreads()
            .subscribe(observer)
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }

    fun onCodeInputted(code: String) {
        if (code.isEmpty()) return

        val observer = SimpleCompletableObserver.Builder()
            .onSubscribe { disposables += it }
            .onError { _codeConfirmMessageLiveData.value = "Неверный код" }
            .onSuccess { _closeScreenLiveData.value = SimpleAction() }
            .build()
        interactor.verifyPhone(phoneNumber, code)
            .setRequestThreads()
            .subscribe(observer)
    }
}