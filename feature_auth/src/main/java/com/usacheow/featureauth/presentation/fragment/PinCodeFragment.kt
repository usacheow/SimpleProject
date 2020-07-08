package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.usacheow.coreuikit.AppStateViewModel
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.biometric.BiometricAuthorizationManager
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.string
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.diprovider.DiProvider
import com.usacheow.featureauth.R
import com.usacheow.featureauth.di.AuthorizationComponent
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInError
import com.usacheow.featureauth.presentation.viewmodels.SignInSuccess
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeForgotButton
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeRootView
import kotlinx.android.synthetic.main.fragment_pin_code.pinCodeView
import javax.inject.Inject

class PinCodeFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_pin_code

    @Inject lateinit var biometricDelegate: BiometricAuthorizationManager
    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val appStateViewModel by injectViewModel<AppStateViewModel>({ requireActivity() }, { viewModelFactory })
    private val viewModel by injectViewModel<PinCodeViewModel> { viewModelFactory }

    companion object {
        fun newInstance() = PinCodeFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
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