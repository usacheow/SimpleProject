package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.usacheow.appshared.AppStateViewModel
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.textinput.hideKeyboard
import com.usacheow.coreui.utils.textinput.onTextChanged
import com.usacheow.coreui.utils.view.*
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

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInViewModel>()

    private var loginInputListener: TextWatcher? = null
    private var passwordInputListener: TextWatcher? = null

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

        doWithTransitionOnParentView {
            binding.signInHeaderView.updateMargins(topPx = when (isKeyboardVisible) {
                true -> 0
                false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
            })

            binding.signUpButton.isVisible = !isKeyboardVisible
            binding.root.updatePadding(top = topPadding, bottom = bottomPadding)
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        loginInputListener = binding.signInLoginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signInLoginInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.signInLoginInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        passwordInputListener = binding.signInPasswordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signInPasswordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.signInPasswordInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        binding.signInButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignInClicked(
                binding.signInLoginInput.text.toString(),
                binding.signInPasswordInput.text.toString()
            )
        }
        binding.signUpButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignUpClicked()
        }
    }

    override fun clearViews() {
        binding.signInLoginInput.removeTextChangedListener(loginInputListener)
        binding.signInPasswordInput.removeTextChangedListener(passwordInputListener)
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(viewLifecycleOwner) { binding.signInLoaderView.root.isVisible = it }
        viewModel.submitButtonEnabled.observe(viewLifecycleOwner) { binding.signInButton.isEnabled = it }
        viewModel.openSignUpScreen.observe(viewLifecycleOwner) { router.openSignUpScreen(this) }
        viewModel.closeScreen.observe(viewLifecycleOwner) { appStateViewModel.onSignIn() }
    }

    private fun getLoginAndPassword() = binding.signInLoginInput.text.toString() to binding.signInPasswordInput.text.toString()
}