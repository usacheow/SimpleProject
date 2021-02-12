package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.usacheow.featureauth.databinding.FragmentSignInByPhoneBinding
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignInWithPhoneFragment : SimpleFragment<FragmentSignInByPhoneBinding>() {

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInWithPhoneViewModel>()
    private val smsCodeViewModel by viewModels<SmsCodeViewModel>()

    companion object {
        fun newInstance() = SignInWithPhoneFragment()
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSignInByPhoneBinding {
        return FragmentSignInByPhoneBinding.inflate(inflater, container, false)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        val bottomPadding = when (isKeyboardVisible) {
            true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        }
        val topPadding = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top

        doWithTransitionOnParentView {
            binding.signInHeaderView.updateMargins(topPx = when (isKeyboardVisible) {
                true -> 0
                false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
            })

            binding.signUpButton.isVisible = !isKeyboardVisible
            binding.signInByPhoneRootView.updatePadding(top = topPadding, bottom = bottomPadding)
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.signInPhoneInput.addPhoneNumberFormatter(
            viewModel::onPhoneChanged,
            viewModel::onPhoneChanged
        )
        binding.signInPhoneInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.signInPhoneInput.clearFocus()
                viewModel.onSubmitClicked(binding.signInPhoneInput.text.toString())
            }
            false
        }
        binding.signInButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignInClicked(binding.signInPhoneInput.text.toString())
        }
        binding.signUpButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignUpClicked()
        }
    }

    override fun subscribe() {
        viewModel.openSignUpScreen.observe(viewLifecycleOwner) { router.openSignUpScreen(this) }
        viewModel.isLoadingState.observe(viewLifecycleOwner) { binding.signInLoaderView.root.isVisible = it }
        viewModel.submitButtonEnabled.observe(viewLifecycleOwner) { binding.signInButton.isEnabled = it }
        viewModel.codeConfirmMessage.observe(viewLifecycleOwner) { smsCodeViewModel.showMessage(it) }
        viewModel.openConfirmScreen.observe(viewLifecycleOwner) { router.openConfirmScreen(this, it) }
        viewModel.closeScreen.observe(viewLifecycleOwner) { appStateViewModel.onSignIn() }
        smsCodeViewModel.code.observe(viewLifecycleOwner) { viewModel.onCodeInputted(it) }
    }
}