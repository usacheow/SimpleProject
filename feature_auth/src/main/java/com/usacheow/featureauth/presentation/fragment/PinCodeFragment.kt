package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.livedata.subscribe
import com.usacheow.coreui.utils.biometric.BiometricAuthorizationManager
import com.usacheow.coreui.utils.ext.PaddingValue
import com.usacheow.coreui.utils.ext.doOnClick
import com.usacheow.coreui.utils.ext.string
import com.usacheow.featureauth.R
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInError
import com.usacheow.featureauth.presentation.viewmodels.SignInSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeForgotButton
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeRootView
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeView
import javax.inject.Inject

@AndroidEntryPoint
class PinCodeFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_pin_code

    @Inject lateinit var biometricDelegate: BiometricAuthorizationManager
    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<PinCodeViewModel>()

    companion object {
        fun newInstance() = PinCodeFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        pinCodeRootView.updatePadding(
            top = insets.systemWindowInsetTop + padding.top,
            bottom = insets.systemWindowInsetBottom + padding.bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        biometricDelegate.init(requireActivity())
        biometricDelegate.onSuccessAction = {
            appStateViewModel.onPinCodeEntered()
        }
        biometricDelegate.onUnavailableAction = {
            pinCodeView.setFingerprintEnabled(false)
        }

        pinCodeView.setHint(string(R.string.pin_view_hint))
        pinCodeView.onBiometricButtonClickedAction = { biometricDelegate.tryShow() }
        pinCodeView.onCodeEnteredAction = { viewModel.onPinCodeInputted(it) }
        pinCodeForgotButton.doOnClick { }
    }

    override fun subscribe() {
        viewModel.isFingerprintAllow.subscribe(viewLifecycleOwner) { isAllow ->
            val isEnabled = isAllow && biometricDelegate.hasBiometricScanner()
            pinCodeView.setFingerprintEnabled(isEnabled)
            if (isEnabled) {
                biometricDelegate.tryShow()
            }
        }
        viewModel.changeAuthState.subscribe(viewLifecycleOwner) {
            when (it) {
                is SignInSuccess -> appStateViewModel.onPinCodeEntered()
                is SignInError -> {
                    pinCodeView.setHint(string(R.string.pin_view_code_error))
                    pinCodeView.showError()
                }
            }
        }
    }
}