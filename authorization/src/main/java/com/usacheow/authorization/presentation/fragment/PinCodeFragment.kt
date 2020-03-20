package com.usacheow.authorization.presentation.fragment

import android.os.Bundle
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.usacheow.authorization.R
import com.usacheow.authorization.di.AuthorizationComponent
import com.usacheow.authorization.presentation.dialog.FingerprintPromptBottomDialog
import com.usacheow.authorization.presentation.router.AuthorizationRouter
import com.usacheow.authorization.presentation.viewmodels.PinCodeViewModel
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.biometric.SignInError
import com.usacheow.coreuikit.utils.biometric.SignInSuccess
import com.usacheow.coreuikit.utils.ext.doOnApplyWindowInsets
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.string
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeForgotButton
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeRootView
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeView
import javax.inject.Inject

class PinCodeFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_pin_code

    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<PinCodeViewModel>(viewModelFactory) }

    companion object {
        fun newInstance() = PinCodeFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        pinCodeRootView.doOnApplyWindowInsets { insets, padding ->
            pinCodeRootView.updatePadding(
                top = insets.systemWindowInsetTop + padding.top,
                bottom = insets.systemWindowInsetBottom + padding.bottom
            )
        }

        pinCodeView.setHint(string(R.string.pin_view_hint))
        pinCodeView.onBiometricButtonClickedAction = { viewModel.displayFingerprintPrompt() }
        pinCodeView.onCodeEnteredAction = { viewModel.onPinCodeInputted(it) }
        pinCodeForgotButton.doOnClick { }
    }

    override fun subscribe() {
        viewModel.isFingerPrintEnabled.subscribe(viewLifecycleOwner) { isEnabled ->
            pinCodeView.setFingerprintEnabled(isEnabled)
            if (isEnabled) {
                viewModel.displayFingerprintPrompt()
            }
        }
        viewModel.isFingerPrintDialog.subscribe(viewLifecycleOwner) { isShowing ->
            when {
                isShowing -> showDialog()
                else -> bottomDialog?.dismiss()
            }
        }
        viewModel.changeAuthState.subscribe(viewLifecycleOwner) {
            when (it) {
                is SignInSuccess -> onSuccess()
                is SignInError -> onError()
            }
        }
    }

    private fun onError() {
        if (bottomDialog?.isShowing == true && bottomDialog is FingerprintPromptBottomDialog) {
            (bottomDialog as FingerprintPromptBottomDialog).showErrorState()
        } else {
            pinCodeView.setHint(string(R.string.pin_view_code_error))
        }
        pinCodeView.showError()
    }

    private fun onSuccess() {
        if (bottomDialog?.isShowing == true && bottomDialog is FingerprintPromptBottomDialog) {
            (bottomDialog as FingerprintPromptBottomDialog).showSuccessState()
        }
        router.openMainScreen(this)
    }

    private fun showDialog() {
        if (bottomDialog == null) {
            bottomDialog = FingerprintPromptBottomDialog(activity!!)
        }
        bottomDialog?.show()
    }
}