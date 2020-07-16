package com.usacheow.featureauth.presentation.fragment

import android.app.Application
import android.os.Bundle
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.livedata.subscribe
import com.usacheow.coreui.utils.ext.PaddingValue
import com.usacheow.coreui.utils.ext.doOnClick
import com.usacheow.coreui.utils.ext.onTextChanged
import com.usacheow.diprovider.DiApp
import com.usacheow.featureauth.R
import com.usacheow.featureauth.di.AuthorizationComponent
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignInWithLoginAndPasswordViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import kotlinx.android.synthetic.main.fragment_sign_in.signInLoaderView
import kotlinx.android.synthetic.main.fragment_sign_in.signInLoginInput
import kotlinx.android.synthetic.main.fragment_sign_in.signInPasswordInput
import kotlinx.android.synthetic.main.fragment_sign_in.signInRootView
import kotlinx.android.synthetic.main.fragment_sign_in.signUpButton
import javax.inject.Inject

class SignInFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_sign_in

    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val appStateViewModel by viewModels<AppStateViewModel>({ requireActivity() }, { viewModelFactory })
    private val viewModel by viewModels<SignInWithLoginAndPasswordViewModel> { viewModelFactory }

    private var loginInputListener: TextWatcher? = null
    private var passwordInputListener: TextWatcher? = null

    companion object {

        const val REQUEST_KEY = "SignInFragment"
        const val IS_SUCCESS = "IS_SUCCESS"

        fun newInstance() = SignInFragment()
    }

    override fun inject(application: Application) {
        AuthorizationComponent.init((application as DiApp).diProvider).inject(this)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        signInRootView.updatePadding(
            top = insets.systemWindowInsetTop + padding.top,
            bottom = insets.systemWindowInsetBottom + padding.bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        loginInputListener = signInLoginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        signInLoginInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInLoginInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        passwordInputListener = signInPasswordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        signInPasswordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signInPasswordInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        signUpButton.doOnClick { viewModel.onSignUpClicked() }
        signInButton.doOnClick {
            viewModel.onSignInClicked(
                signInLoginInput.text.toString(),
                signInPasswordInput.text.toString()
            )
        }
    }

    override fun clearViews() {
        signInLoginInput.removeTextChangedListener(loginInputListener)
        signInPasswordInput.removeTextChangedListener(passwordInputListener)
    }

    override fun subscribe() {
        viewModel.isLoadingState.subscribe(viewLifecycleOwner) { signInLoaderView.isVisible = it }
        viewModel.submitButtonEnabled.subscribe(viewLifecycleOwner) { signInButton.isEnabled = it }
        viewModel.openSignUpScreen.subscribe(viewLifecycleOwner) { router.openSignUpScreen(this) }
        viewModel.closeScreen.subscribe(viewLifecycleOwner) { appStateViewModel.onSignIn() }
    }

    private fun getLoginAndPassword() = signInLoginInput.text.toString() to signInPasswordInput.text.toString()
}