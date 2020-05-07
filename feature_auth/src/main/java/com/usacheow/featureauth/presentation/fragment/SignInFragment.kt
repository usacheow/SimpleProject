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
    private val viewModel by injectViewModel<SignInWithLoginAndPasswordViewModel> { viewModelFactory }

    private var loginInputListener: TextWatcher? = null
    private var passwordInputListener: TextWatcher? = null

    companion object {

        const val REQUEST_KEY = "SignInFragment"
        const val IS_SUCCESS = "IS_SUCCESS"

        fun newInstance() = SignInFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
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
        viewModel.closeScreen.subscribe(viewLifecycleOwner) { setFragmentResult(REQUEST_KEY, bundleOf(IS_SUCCESS to true)) }
    }

    private fun getLoginAndPassword() = signInLoginInput.text.toString() to signInPasswordInput.text.toString()
}