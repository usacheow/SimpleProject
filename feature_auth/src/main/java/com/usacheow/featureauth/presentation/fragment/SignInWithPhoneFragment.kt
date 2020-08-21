package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.app_shared.otp.SmsCodeViewModel
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.livedata.subscribe
import com.usacheow.coreui.utils.textinput.onTextChanged
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.featureauth.R
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInButton
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInByPhoneRootView
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInLoaderView
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInPhoneInput
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signUpButton
import javax.inject.Inject

@AndroidEntryPoint
class SignInWithPhoneFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_sign_in_by_phone

    @Inject lateinit var router: AuthorizationRouter
    private val appStateViewModel by activityViewModels<AppStateViewModel>()
    private val viewModel by viewModels<SignInWithPhoneViewModel>()
    private val smsCodeViewModel by viewModels<SmsCodeViewModel>()

    private var signInPhoneInputListener: TextWatcher? = null

    companion object {
        fun newInstance() = SignInWithPhoneFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        signInByPhoneRootView.updatePadding(
            top = insets.systemWindowInsetTop + padding.top,
            bottom = insets.systemWindowInsetBottom + padding.bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        signInPhoneInputListener = signInPhoneInput.onTextChanged { viewModel.onPhoneChanged(it) }
        signInPhoneInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInPhoneInput.clearFocus()
                viewModel.onSubmitClicked(signInPhoneInput.text.toString())
            }
            false
        }
        signInButton.doOnClick { viewModel.onSignInClicked(signInPhoneInput.text.toString()) }
        signUpButton.doOnClick { viewModel.onSignUpClicked() }
    }

    override fun clearViews() {
        signInPhoneInput.removeTextChangedListener(signInPhoneInputListener)
    }

    override fun subscribe() {
        viewModel.openSignUpScreen.subscribe(viewLifecycleOwner) { router.openSignUpScreen(this) }
        viewModel.isLoadingState.subscribe(viewLifecycleOwner) { signInLoaderView.isVisible = it }
        viewModel.submitButtonEnabled.subscribe(viewLifecycleOwner) { signInButton.isEnabled = it }
        viewModel.codeConfirmMessage.subscribe(viewLifecycleOwner) { smsCodeViewModel.showMessage(it) }
        viewModel.openConfirmScreen.subscribe(viewLifecycleOwner) { router.openConfirmScreen(this, it) }
        viewModel.closeScreen.subscribe(viewLifecycleOwner) { appStateViewModel.onSignIn() }
        smsCodeViewModel.code.subscribe(viewLifecycleOwner) { viewModel.onCodeInputted(it) }
    }
}