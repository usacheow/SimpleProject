package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.MarginTop
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.coreui.uikit.helper.doOnActionClick
import com.usacheow.coreui.uikit.helper.onTextChanged
import com.usacheow.coreui.uikit.helper.updateMargins
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyInsets
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.helper.hideIme
import com.usacheow.coreui.uikit.helper.isImeVisible
import com.usacheow.coreui.uikit.helper.toPx
import com.usacheow.featureauth.databinding.FragmentSignUpBinding
import com.usacheow.featureauth.presentation.navigation.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignUpFragment : SimpleFragment<FragmentSignUpBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentSignUpBinding::inflate,
    )

    @Inject lateinit var router: AuthorizationRouter
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        val topMargin = when (insets.isImeVisible()) {
            true -> 0
            false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
        }

        binding.headerView.updateMargins(MarginTop(topMargin))
        binding.scrollView.applyInsets(top = insets.getTopInset(), bottom = insets.getBottomInset(needIme = true))

        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.loginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.passwordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }

        binding.loginInput.doOnActionClick(EditorInfo.IME_ACTION_NEXT) {
            binding.passwordInput.requestFocus()
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        binding.passwordInput.doOnActionClick(EditorInfo.IME_ACTION_DONE) {
            binding.passwordInput.clearFocus()
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }

        binding.signUpButton.doOnClick {
            windowInsetsController?.hideIme()
            viewModel.onSignInClicked(
                binding.loginInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(viewLifecycleOwner) { binding.loaderView.isVisible = it }
        viewModel.errorState.observe(viewLifecycleOwner) {
            // todo: implement
        }
        viewModel.isSubmitButtonEnabledState.observe(viewLifecycleOwner) { binding.signUpButton.isEnabled = it }
        viewModel.openNextScreenAction.observe(viewLifecycleOwner, router::apply)
    }

    private fun getLoginAndPassword() =
        binding.loginInput.text.toString() to binding.passwordInput.text.toString()
}