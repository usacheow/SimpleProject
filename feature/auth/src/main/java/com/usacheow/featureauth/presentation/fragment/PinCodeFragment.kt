package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.usacheow.featureauth.presentation.biometric.BiometricEnterManager
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.helper.PaddingValue
import com.usacheow.coreuiview.helper.applyInsets
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.getBottomInset
import com.usacheow.coreuiview.helper.getTopInset
import com.usacheow.coreuiview.helper.string
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.featureauth.databinding.FragmentPinCodeBinding
import com.usacheow.featureauth.presentation.navigation.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.corecommon.R as CoreR

@AndroidEntryPoint
class PinCodeFragment : SimpleFragment<FragmentPinCodeBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentPinCodeBinding::inflate,
    )

    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var biometricDelegate: BiometricEnterManager
    private val viewModel by viewModels<PinCodeViewModel>()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.pinCodeRootView.applyInsets(
            top = insets.getTopInset() + padding.top,
            bottom = insets.getBottomInset() + padding.bottom,
        )
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.pinCodeView.setHint(string(CoreR.string.pin_view_hint))
        binding.pinCodeView.onBiometricButtonClickedAction = { viewModel.onBiometricClicked() }
        binding.pinCodeView.onCodeEnteredAction = { viewModel.onPinCodeInputted(it) }
        binding.pinCodeForgotButton.doOnClick {
            // todo: implement
        }

        biometricDelegate.run {
            onSuccessAction = { viewModel.onBiometricSucceeded(it) }
            onUnavailableAction = { binding.pinCodeView.setBiometricEnabled(false) }
        }
    }

    override fun subscribe() {
        viewModel.isBiometricAllowState.observe(viewLifecycleOwner) { isAllow ->
            binding.pinCodeView.setBiometricEnabled(isAllow && biometricDelegate.isBiometricAvailable())
            if (isAllow) {
                requireView().post {
                    viewModel.onBiometricClicked()
                }
            }
        }
        viewModel.openBiometricScreenAction.observe(viewLifecycleOwner) {
            biometricDelegate.tryShow()
        }
        viewModel.openNextScreenAction.observe(viewLifecycleOwner, router::apply)
        viewModel.authState.observe(viewLifecycleOwner) {
            when (it) {
                is SignInResult.SignInSuccess -> {}

                is SignInResult.SignInError -> {
                    binding.pinCodeView.setHint(string(CoreR.string.pin_view_code_error))
                    binding.pinCodeView.showError()
                }

                is SignInResult.SignInInput -> {
                    binding.pinCodeView.resetState()
                }
            }
        }
    }
}