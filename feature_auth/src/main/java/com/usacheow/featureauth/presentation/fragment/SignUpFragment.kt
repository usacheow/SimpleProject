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
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.textinput.hideKeyboard
import com.usacheow.coreui.utils.textinput.onTextChanged
import com.usacheow.coreui.utils.view.*
import com.usacheow.featureauth.databinding.FragmentSignUpBinding
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignUpWithLoginAndPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignUpFragment : SimpleFragment<FragmentSignUpBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentSignUpBinding::inflate,
    )

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignUpWithLoginAndPasswordViewModel>()

    private var loginInputListener: TextWatcher? = null
    private var passwordInputListener: TextWatcher? = null

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        val bottomPadding = when (isKeyboardVisible) {
            true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        }
        val topPadding = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top

        doWithTransitionOnParentView {
            binding.signUpHeaderView.updateMargins(topPx = when (isKeyboardVisible) {
                true -> 0
                false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
            })

            binding.signUpRootView.updatePadding(top = topPadding, bottom = bottomPadding)
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        loginInputListener = binding.signUpLoginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signUpLoginInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.signUpLoginInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        passwordInputListener = binding.signUpPasswordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signUpPasswordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.signUpPasswordInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        binding.signUpButton.doOnClick {
            requireView().hideKeyboard()
            viewModel.onSignInClicked(
                binding.signUpLoginInput.text.toString(),
                binding.signUpPasswordInput.text.toString()
            )
        }
    }

    override fun clearViews() {
        binding.signUpLoginInput.removeTextChangedListener(loginInputListener)
        binding.signUpPasswordInput.removeTextChangedListener(passwordInputListener)
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(viewLifecycleOwner) { binding.signUpLoaderView.root.isVisible = it }
        viewModel.submitButtonEnabled.observe(viewLifecycleOwner) { binding.signUpButton.isEnabled = it }
        viewModel.openMainScreen.observe(viewLifecycleOwner) { appStateViewModel.onSignUp() }
    }

    private fun getLoginAndPassword() = binding.signUpLoginInput.text.toString() to binding.signUpPasswordInput.text.toString()
}