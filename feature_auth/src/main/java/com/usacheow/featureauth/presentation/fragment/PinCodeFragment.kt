package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.appstate.AppStateViewModel
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.biometric.BiometricEnterManager
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.coreui.utils.view.string
import com.usacheow.featureauth.R
import com.usacheow.featureauth.databinding.FragmentPinCodeBinding
import com.usacheow.featureauth.presentation.navigation.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInResult
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

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.pinCodeRootView.updatePadding(
            top = insets.getTopInset() + padding.top,
            bottom = insets.getBottomInset() + padding.bottom
        )
        return insets
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
                is SignInResult.SignInSuccess -> appStateViewModel.onPinCodeEntered()
                is SignInResult.SignInError -> {
                    binding.pinCodeView.setHint(string(R.string.pin_view_code_error))
                    binding.pinCodeView.showError()
                }
                is SignInResult.SignInInput -> {
                    binding.pinCodeView.resetState()
                }
            }
        }
    }
}