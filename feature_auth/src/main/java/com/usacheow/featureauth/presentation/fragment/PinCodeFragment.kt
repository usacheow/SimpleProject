package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.WindowInsets
import androidx.core.os.bundleOf
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.biometric.SignInError
import com.usacheow.coreuikit.utils.biometric.SignInSuccess
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.string
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.diprovider.DiProvider
import com.usacheow.featureauth.R
import com.usacheow.featureauth.di.AuthorizationComponent
import com.usacheow.featureauth.presentation.dialog.FingerprintPromptBottomDialog
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
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

        const val REQUEST_KEY = "PinCodeFragment"
        const val IS_SUCCESS = "IS_SUCCESS"

        fun newInstance() = PinCodeFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
    }

    override fun onApplyWindowInsets(insets: WindowInsets, padding: PaddingValue) {
        pinCodeRootView.updatePadding(
            top = insets.systemWindowInsetTop + padding.top,
            bottom = insets.systemWindowInsetBottom + padding.bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
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
        setFragmentResult(REQUEST_KEY, bundleOf(IS_SUCCESS to true))
    }

    private fun showDialog() {
        if (bottomDialog == null) {
            bottomDialog = FingerprintPromptBottomDialog(requireActivity())
        }
        bottomDialog?.show()
    }
}