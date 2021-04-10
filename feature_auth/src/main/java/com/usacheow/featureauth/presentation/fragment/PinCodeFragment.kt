package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.appshared.AppStateViewModel
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.biometric.BiometricEnterManager
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.string
import com.usacheow.featureauth.R
import com.usacheow.featureauth.databinding.FragmentPinCodeBinding
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInInput
import com.usacheow.featureauth.presentation.viewmodels.SignInError
import com.usacheow.featureauth.presentation.viewmodels.SignInSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PinCodeFragment : SimpleFragment<FragmentPinCodeBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentPinCodeBinding::inflate,
    )

    @Inject lateinit var biometricDelegate: BiometricEnterManager
    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<PinCodeViewModel>()

    companion object {
        fun newInstance() = PinCodeFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.pinCodeRootView.updatePadding(
            top = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top + padding.top,
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom + padding.bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        biometricDelegate.init(requireActivity())
        biometricDelegate.onSuccessAction = {
            appStateViewModel.onPinCodeEntered()
        }
        biometricDelegate.onUnavailableAction = {
            binding.pinCodeView.setFingerprintEnabled(false)
        }

        binding.pinCodeView.setHint(string(R.string.pin_view_hint))
        binding.pinCodeView.onBiometricButtonClickedAction = { biometricDelegate.tryShow() }
        binding.pinCodeView.onCodeEnteredAction = { viewModel.onPinCodeInputted(it) }
        binding.pinCodeForgotButton.doOnClick {
            // todo: implement
        }
    }

    override fun subscribe() {
        viewModel.isFingerprintAllowState.observe(lifecycle) { isAllow ->
            val isEnabled = isAllow && biometricDelegate.hasBiometricScanner()
            binding.pinCodeView.setFingerprintEnabled(isEnabled)
            if (isEnabled) {
                binding.root.post { biometricDelegate.tryShow() }
            }
        }
        viewModel.changeAuthState.observe(lifecycle) {
            when (it) {
                is SignInSuccess -> appStateViewModel.onPinCodeEntered()
                is SignInError -> {
                    binding.pinCodeView.setHint(string(R.string.pin_view_code_error))
                    binding.pinCodeView.showError()
                }
                is SignInInput -> {
                    binding.pinCodeView.resetState()
                }
            }
        }
    }
}