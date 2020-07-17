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
import com.usacheow.di.DiApp
import com.usacheow.featureauth.R
import com.usacheow.featureauth.di.AuthorizationComponent
import com.usacheow.featureauth.presentation.router.AuthorizationRouter
import com.usacheow.featureauth.presentation.viewmodels.SignUpWithLoginAndPasswordViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.signUpButton
import kotlinx.android.synthetic.main.fragment_sign_up.signUpLoaderView
import kotlinx.android.synthetic.main.fragment_sign_up.signUpLoginInput
import kotlinx.android.synthetic.main.fragment_sign_up.signUpPasswordInput
import kotlinx.android.synthetic.main.fragment_sign_up.signUpRootView
import javax.inject.Inject

class SignUpFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_sign_up

    @Inject lateinit var router: AuthorizationRouter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val appStateViewModel by viewModels<AppStateViewModel>({ requireActivity() }, { viewModelFactory })
    private val viewModel by viewModels<SignUpWithLoginAndPasswordViewModel> { viewModelFactory }

    private var loginInputListener: TextWatcher? = null
    private var passwordInputListener: TextWatcher? = null

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun inject(application: Application) {
        AuthorizationComponent.init((application as DiApp).diProvider).inject(this)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        signUpRootView.updatePadding(
            top = insets.systemWindowInsetTop + padding.top,
            bottom = insets.systemWindowInsetBottom + padding.bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        loginInputListener = signUpLoginInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        signUpLoginInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signUpLoginInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        passwordInputListener = signUpPasswordInput.onTextChanged {
            viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
        }
        signUpPasswordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signUpPasswordInput.clearFocus()
                viewModel.onDataChanged(getLoginAndPassword().first, getLoginAndPassword().second)
            }
            false
        }
        signUpButton.doOnClick {
            viewModel.onSignInClicked(
                signUpLoginInput.text.toString(),
                signUpPasswordInput.text.toString()
            )
        }
    }

    override fun clearViews() {
        signUpLoginInput.removeTextChangedListener(loginInputListener)
        signUpPasswordInput.removeTextChangedListener(passwordInputListener)
    }

    override fun subscribe() {
        viewModel.isLoadingState.subscribe(viewLifecycleOwner) { signUpLoaderView.isVisible = it }
        viewModel.submitButtonEnabled.subscribe(viewLifecycleOwner) { signUpButton.isEnabled = it }
        viewModel.openMainScreen.subscribe(viewLifecycleOwner) { appStateViewModel.onSignUp() }
    }

    private fun getLoginAndPassword() = signUpLoginInput.text.toString() to signUpPasswordInput.text.toString()
}