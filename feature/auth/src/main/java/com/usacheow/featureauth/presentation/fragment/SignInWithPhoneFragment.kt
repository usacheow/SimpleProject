package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.usacheow.corecommon.container.toTextSource
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.MarginValues
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.addPhoneNumberFormatter
import com.usacheow.coreuiview.tools.applyInsets
import com.usacheow.coreuiview.tools.doOnActionClick
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreuiview.tools.hideIme
import com.usacheow.coreuiview.tools.isImeVisible
import com.usacheow.coreuiview.tools.makeSnackbar
import com.usacheow.coreuiview.tools.resource.toPx
import com.usacheow.coreuiview.tools.applyMargins
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.featureauth.databinding.FragmentSignInWithPhoneBinding
import com.usacheow.featureauth.presentation.navigation.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val DEFAULT_HEADER_MARGIN_TOP_DP = 120

@AndroidEntryPoint
class SignInWithPhoneFragment : SimpleFragment<FragmentSignInWithPhoneBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentSignInWithPhoneBinding::inflate,
    )

    @Inject lateinit var router: AuthorizationRouter
    private val viewModel by viewModels<SignInWithPhoneViewModel>()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        val topMargin = when (insets.isImeVisible()) {
            true -> 0
            false -> DEFAULT_HEADER_MARGIN_TOP_DP.toPx
        }

        binding.signUpButton.isVisible = !insets.isImeVisible()
        binding.headerView.applyMargins(MarginValues(top = topMargin))
        binding.scrollView.applyInsets(top = insets.getTopInset(), bottom = insets.getBottomInset(needIme = true))

        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.phoneInput.addPhoneNumberFormatter(
            viewModel::onPhoneChanged,
            viewModel::onPhoneChanged,
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
        viewModel.isLoadingState.observe(viewLifecycleOwner) { binding.loaderView.isVisible = it }
        viewModel.errorState.observe(viewLifecycleOwner) { it?.toTextSource()?.let(requireView()::makeSnackbar) }
        viewModel.isSubmitButtonEnabledState.observe(viewLifecycleOwner) { binding.signInButton.isEnabled = it }
        viewModel.openSignUpScreenAction.observe(viewLifecycleOwner, router::toSignUpFlow)
        viewModel.openNextScreenAction.observe(viewLifecycleOwner, router::apply)
    }
}