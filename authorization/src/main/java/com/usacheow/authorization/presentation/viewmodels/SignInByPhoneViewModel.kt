package com.usacheow.authorization.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usacheow.authorization.domain.commands.AuthByCodeCommand
import com.usacheow.authorization.domain.commands.SendCodeByPhoneCommand
import com.usacheow.coredata.Storage
import com.usacheow.coredata.error.ErrorProcessorImpl
import com.usacheow.coredata.setRequestThreads
import com.usacheow.coreuikit.utils.ext.normalizedPhoneNumber
import com.usacheow.coreuikit.viewmodels.SimpleRxViewModel
import com.usacheow.coreuikit.viewmodels.livedata.ActionLiveData
import com.usacheow.coreuikit.viewmodels.livedata.SimpleAction
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

private const val EXPECTED_PHONE_NUMBER_LENGTH = 10
private const val CONFIRM_CODE_LENGTH = 4

class SignInByPhoneViewModel
@Inject constructor(
    errorProcessor: ErrorProcessorImpl,
    private val sendCodeByPhoneCommand: SendCodeByPhoneCommand,
    private val authByCodeCommand: AuthByCodeCommand,
    private val storage: Storage
) : SimpleRxViewModel(errorProcessor) {

    val isLoadingState: LiveData<Boolean> get() = _isLoadingStateLiveData
    private val _isLoadingStateLiveData by lazy { MutableLiveData<Boolean>() }

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
        val phoneNumber = phone.normalizedPhoneNumber()
        if (!isValidPhoneNumber(phoneNumber)) return

        disposables.clear()
        disposables += sendCodeByPhoneCommand.execute(phone)
            .doOnSubscribe { _isLoadingStateLiveData.postValue(true) }
            .setRequestThreads()
            .defaultSubscribe {
                storage.phoneNumber = phone
                _isLoadingStateLiveData.value = false
                _openConfirmScreenLiveData.value = CONFIRM_CODE_LENGTH
            }
    }

    fun onSignUpClicked() {
        _openSignUpScreenLiveData.value = SimpleAction()
    }

    fun onCodeInputted(code: String) {
        if (code.isEmpty()) return

        disposables += authByCodeCommand.execute(code)
            .setRequestThreads()
            .subscribe {
                _codeConfirmStateLiveData.value = null
                _openMainScreenLiveData.value = SimpleAction()
            }
//            .subscribe { _codeConfirmStateLiveData.value = "Неверный код" }
    }
}