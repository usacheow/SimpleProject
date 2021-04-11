package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.appshared.AppStateViewModel
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.MarginTop
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.textinput.hideKeyboard
import com.usacheow.coreui.utils.textinput.doOnActionClick
import com.usacheow.coreui.utils.textinput.onTextChanged
import com.usacheow.coreui.utils.updateMargins
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featureauth.databinding.FragmentSignInBinding
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignInFragment : SimpleFragment<FragmentSignInBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentSignInBinding::inflate,
    )

    @Inject
    lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInViewModel>()

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        val bottomPadding = when (isKeyboardVisible) {
            true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        }
        val topPadding = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top

        val topMargin = when (isKeyboardVisible) {
            true -> 0
            false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
        }
        binding.signInHeaderView.updateMargins(MarginTop(topMargin))

        binding.signUpButton.isVisible = !isKeyboardVisible
        binding.root.updatePadding(top = topPadding, bottom = bottomPadding)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.signInLoginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signInPasswordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }

        binding.signInLoginInput.doOnActionClick(EditorInfo.IME_ACTION_NEXT) {
            binding.signInPasswordInput.requestFocus()
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signInPasswordInput.doOnActionClick(EditorInfo.IME_ACTION_DONE) {
            binding.signInPasswordInput.clearFocus()
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }

        binding.signInButton.doOnClick {
            binding.root.hideKeyboard()
            viewModel.onSignInClicked(
                binding.signInLoginInput.text.toString(),
                binding.signInPasswordInput.text.toString()
            )
        }
        binding.signUpButton.doOnClick {
            binding.root.hideKeyboard()
            viewModel.onSignUpClicked()
        }
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(lifecycle) { binding.signInLoaderView.root.isVisible = it }
        viewModel.submitButtonEnabledState.observe(lifecycle) { binding.signInButton.isEnabled = it }
        viewModel.openSignUpScreenAction.observe(lifecycle) { router.openSignUpScreen() }
        viewModel.closeScreenAction.observe(lifecycle) { appStateViewModel.onSignIn() }
    }

    private fun getLoginAndPassword() =
        binding.signInLoginInput.text.toString() to binding.signInPasswordInput.text.toString()
}