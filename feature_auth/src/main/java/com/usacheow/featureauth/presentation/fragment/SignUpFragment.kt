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
import com.usacheow.coreui.utils.textinput.doOnActionClicked
import com.usacheow.coreui.utils.textinput.onTextChanged
import com.usacheow.coreui.utils.updateMargins
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featureauth.databinding.FragmentSignUpBinding
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignUpFragment : SimpleFragment<FragmentSignUpBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentSignUpBinding::inflate,
    )

    @Inject
    lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignUpViewModel>()

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

        val topMargin = when (isKeyboardVisible) {
            true -> 0
            false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
        }
        binding.signUpHeaderView.updateMargins(MarginTop(topMargin))

        binding.signUpRootView.updatePadding(top = topPadding, bottom = bottomPadding)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.signUpLoginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signUpPasswordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }

        binding.signUpLoginInput.doOnActionClicked(EditorInfo.IME_ACTION_NEXT) {
            binding.signUpPasswordInput.requestFocus()
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.signUpPasswordInput.doOnActionClicked(EditorInfo.IME_ACTION_DONE) {
            binding.signUpPasswordInput.clearFocus()
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }

        binding.signUpButton.doOnClick {
            binding.root.hideKeyboard()
            viewModel.onSignInClicked(
                binding.signUpLoginInput.text.toString(),
                binding.signUpPasswordInput.text.toString()
            )
        }
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(lifecycle) { binding.signUpLoaderView.root.isVisible = it }
        viewModel.isSubmitButtonEnabledState.observe(lifecycle) { binding.signUpButton.isEnabled = it }
        viewModel.openMainScreenAction.observe(lifecycle) { appStateViewModel.onSignUp() }
    }

    private fun getLoginAndPassword() =
        binding.signUpLoginInput.text.toString() to binding.signUpPasswordInput.text.toString()
}