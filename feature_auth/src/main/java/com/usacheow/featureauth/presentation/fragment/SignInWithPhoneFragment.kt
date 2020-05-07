package com.usacheow.featureauth.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.onTextChanged
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.diprovider.DiProvider
import com.usacheow.featureauth.R
import com.usacheow.featureauth.di.AuthorizationComponent
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import com.usacheow.otp.SmsCodeViewModel
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInButton
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInByPhoneRootView
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInLoaderView
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInPhoneInput
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signUpButton
import javax.inject.Inject

class SignInWithPhoneFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_sign_in_by_phone

    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by injectViewModel<SignInWithPhoneViewModel> { viewModelFactory }
    private val smsCodeViewModel by injectViewModel<SmsCodeViewModel> { viewModelFactory }

    private var signInPhoneInputListener: TextWatcher? = null

    companion object {

        const val REQUEST_KEY = "SignInWithPhoneFragment"
        const val IS_SUCCESS = "IS_SUCCESS"

        fun newInstance() = SignInWithPhoneFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
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
        viewModel.openConfirmScreen.subscribe(viewLifecycleOwner) { router.openConfirmScreen(this, it, viewModelFactory) }
        viewModel.closeScreen.subscribe(viewLifecycleOwner) { setFragmentResult(REQUEST_KEY, bundleOf(IS_SUCCESS to true)) }
        smsCodeViewModel.code.subscribe(viewLifecycleOwner) { viewModel.onCodeInputted(it) }
    }
}