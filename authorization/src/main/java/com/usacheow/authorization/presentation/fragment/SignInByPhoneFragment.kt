package com.usacheow.authorization.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.WindowInsets
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.usacheow.authorization.R
import com.usacheow.authorization.di.AuthorizationComponent
import com.usacheow.authorization.presentation.router.AuthorizationRouter
import com.usacheow.authorization.presentation.viewmodels.SignInWithPhoneViewModel
import com.usacheow.authorization.presentation.viewmodels.SmsCodeViewModel
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.onTextChanged
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInButton
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInByPhoneRootView
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInLoaderView
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signInPhoneInput
import kotlinx.android.synthetic.main.fragment_sign_in_by_phone.signUpButton
import javax.inject.Inject

class SignInByPhoneFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_sign_in_by_phone

    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<SignInWithPhoneViewModel>(viewModelFactory) }
    private val smsCodeViewModel by lazy { injectViewModel<SmsCodeViewModel>(viewModelFactory) }

    private var signInPhoneInputListener: TextWatcher? = null

    companion object {
        fun newInstance() = SignInByPhoneFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
    }

    override fun onApplyWindowInsets(insets: WindowInsets, padding: PaddingValue) {
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
        viewModel.isLoadingState.subscribe(viewLifecycleOwner) { signInLoaderView.isVisible = it }
        viewModel.submitButtonEnabled.subscribe(viewLifecycleOwner) { signInButton.isEnabled = it }
        viewModel.codeConfirmState.subscribe(viewLifecycleOwner) { smsCodeViewModel.changeState(it) }
        viewModel.openSignUpScreen.subscribe(viewLifecycleOwner) { router.openSignUpScreen(this) }
        viewModel.openConfirmScreen.subscribe(viewLifecycleOwner) { router.openConfirmScreen(this, it) }
        viewModel.openMainScreen.subscribe(viewLifecycleOwner) { router.openMainScreen(this) }
        smsCodeViewModel.code.subscribe(viewLifecycleOwner) { viewModel.onCodeInputted(it) }
    }
}