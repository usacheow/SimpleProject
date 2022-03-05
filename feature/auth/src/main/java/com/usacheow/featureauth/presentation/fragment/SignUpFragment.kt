package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.MarginValues
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyInsets
import com.usacheow.coreuiview.tools.doOnActionClick
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreuiview.tools.hideIme
import com.usacheow.coreuiview.tools.isImeVisible
import com.usacheow.coreuiview.tools.onTextChanged
import com.usacheow.coreuiview.tools.resource.toPx
import com.usacheow.coreuiview.tools.applyMargins
import com.usacheow.coreui.viewmodel.observe
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

        binding.headerView.applyMargins(MarginValues(top = topMargin))
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