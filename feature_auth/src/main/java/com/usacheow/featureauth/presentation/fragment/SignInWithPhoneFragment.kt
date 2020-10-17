package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.app_shared.otp.SmsCodeViewModel
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.textinput.addPhoneNumberFormatter
import com.usacheow.coreui.utils.textinput.hideKeyboard
import com.usacheow.coreui.utils.view.*
import com.usacheow.featureauth.R
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.*
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignInWithPhoneFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_sign_in_by_phone

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInWithPhoneViewModel>()
    private val smsCodeViewModel by viewModels<SmsCodeViewModel>()

    companion object {
        fun newInstance() = SignInWithPhoneFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        val bottomPadding = when (isKeyboardVisible) {
            true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        }
        val topPadding = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top

        doWithTransitionOnParentView {
            signInHeaderView.updateMargins(topPx = when (isKeyboardVisible) {
                true -> 0
                false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
            })

            signUpButton.isVisible = !isKeyboardVisible
            signInByPhoneRootView.updatePadding(top = topPadding, bottom = bottomPadding)
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        signInPhoneInput.addPhoneNumberFormatter(
            viewModel::onPhoneChanged,
            viewModel::onPhoneChanged
        )
        signInPhoneInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInPhoneInput.clearFocus()
                viewModel.onSubmitClicked(signInPhoneInput.text.toString())
            }
            false
        }
        signInButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignInClicked(signInPhoneInput.text.toString())
        }
        signUpButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignUpClicked()
        }
    }

    override fun subscribe() {
        viewModel.openSignUpScreen.observe(viewLifecycleOwner) { router.openSignUpScreen(this) }
        viewModel.isLoadingState.observe(viewLifecycleOwner) { signInLoaderView.isVisible = it }
        viewModel.submitButtonEnabled.observe(viewLifecycleOwner) { signInButton.isEnabled = it }
        viewModel.codeConfirmMessage.observe(viewLifecycleOwner) { smsCodeViewModel.showMessage(it) }
        viewModel.openConfirmScreen.observe(viewLifecycleOwner) { router.openConfirmScreen(this, it) }
        viewModel.closeScreen.observe(viewLifecycleOwner) { appStateViewModel.onSignIn() }
        smsCodeViewModel.code.observe(viewLifecycleOwner) { viewModel.onCodeInputted(it) }
    }
}