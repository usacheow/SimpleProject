package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.appstate.AppStateViewModel
import com.usacheow.appstate.otp.OtpCodeState
import com.usacheow.appstate.otp.OtpFeatureConnector
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.MarginTop
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.textinput.addPhoneNumberFormatter
import com.usacheow.coreui.utils.textinput.doOnActionClick
import com.usacheow.coreui.utils.updateMargins
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featureauth.databinding.FragmentSignInByPhoneBinding
import com.usacheow.featureauth.presentation.navigation.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignInWithPhoneFragment : SimpleFragment<FragmentSignInByPhoneBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentSignInByPhoneBinding::inflate,
    )

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInWithPhoneViewModel>()
    private val otpStateViewModel by viewModels<OtpFeatureConnector>({ requireParentFragment() })

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        val topMargin = when (insets.isImeVisible()) {
            true -> 0
            false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
        }

        binding.signUpButton.isVisible = !insets.isImeVisible()
        binding.headerView.updateMargins(MarginTop(topMargin))
        binding.scrollView.updatePadding(top = insets.getTopInset(), bottom = insets.getBottomInset(needIme = true))

        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.phoneInput.addPhoneNumberFormatter(
            viewModel::onPhoneChanged,
            viewModel::onPhoneChanged
        )
        binding.phoneInput.doOnActionClick(EditorInfo.IME_ACTION_DONE) {
            binding.phoneInput.clearFocus()
            viewModel.onSubmitClicked(binding.phoneInput.text.toString())
        }
        binding.signInButton.doOnClick {
            windowInsetsController?.hideIme()
            viewModel.onSignInClicked(binding.phoneInput.text.toString())
        }
        binding.signUpButton.doOnClick {
            windowInsetsController?.hideIme()
            viewModel.onSignUpClicked()
        }
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(lifecycle) { binding.loaderView.root.isVisible = it }
        viewModel.errorState.observe(lifecycle) {
            // todo: implement
        }
        viewModel.isSubmitButtonEnabledState.observe(lifecycle) { binding.signInButton.isEnabled = it }
        viewModel.openConfirmScreenAction.observe(lifecycle) { router.openSmsCodeScreen(it) }
        viewModel.openSignUpScreenAction.observe(lifecycle) { router.openSignUpScreen() }
        viewModel.closeAuthFlowAction.observe(lifecycle) { appStateViewModel.onSignedIn() }
        viewModel.closeSmsCodeScreenAction.observe(lifecycle) { otpStateViewModel.notifyAboutSuccess() }
        viewModel.codeConfirmMessageState.observe(lifecycle) { otpStateViewModel.notifyAboutError(it) }
        otpStateViewModel.updateCodeStateAction.observe(lifecycle) {
            when (it) {
                is OtpCodeState.OtpCodeInputted -> viewModel.onCodeInputted(it.code)
                is OtpCodeState.OtpCodeRequested -> viewModel.onResendClicked()
            }
        }
    }
}