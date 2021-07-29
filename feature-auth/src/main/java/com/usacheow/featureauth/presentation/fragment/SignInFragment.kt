package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.appstate.AppStateViewModel
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.MarginTop
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.textinput.doOnActionClick
import com.usacheow.coreui.utils.textinput.onTextChanged
import com.usacheow.coreui.utils.updateMargins
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featureauth.databinding.FragmentSignInBinding
import com.usacheow.featureauth.presentation.navigation.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignInFragment : SimpleFragment<FragmentSignInBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentSignInBinding::inflate,
    )

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInViewModel>()

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
        binding.loginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword())
        }
        binding.passwordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword())
        }

        binding.loginInput.doOnActionClick(EditorInfo.IME_ACTION_NEXT) {
            binding.passwordInput.requestFocus()
            viewModel.onDataChanged(getLoginAndPassword())
        }
        binding.passwordInput.doOnActionClick(EditorInfo.IME_ACTION_DONE) {
            binding.passwordInput.clearFocus()
            viewModel.onDataChanged(getLoginAndPassword())
        }

        binding.signInButton.doOnClick {
            windowInsetsController?.hideIme()
            viewModel.onSignInClicked(
                binding.loginInput.text.toString(),
                binding.passwordInput.text.toString()
            )
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
        viewModel.submitButtonEnabledState.observe(lifecycle) { binding.signInButton.isEnabled = it }
        viewModel.openSignUpScreenAction.observe(lifecycle) { router.toSignUpFlow() }
        viewModel.closeScreenAction.observe(lifecycle) { appStateViewModel.onSignedIn() }
    }

    private fun getLoginAndPassword() =
        binding.loginInput.text.toString() to binding.passwordInput.text.toString()
}