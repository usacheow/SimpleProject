package com.usacheow.authorization.presentation.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import com.usacheow.authorization.R
import com.usacheow.authorization.di.AuthorizationComponent
import com.usacheow.authorization.presentation.router.AuthorizationRouter
import com.usacheow.authorization.presentation.viewmodels.SignUpViewModel
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.doOnApplyWindowInsets
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.onTextChanged
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.diprovider.DiProvider
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
    private val viewModel by lazy { injectViewModel<SignUpViewModel>(viewModelFactory) }

    private var loginInputListener: TextWatcher? = null
    private var passwordInputListener: TextWatcher? = null

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun inject(diProvider: DiProvider) {
        AuthorizationComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        signUpRootView.doOnApplyWindowInsets { insets, padding ->
            signUpRootView.updatePadding(
                top = insets.systemWindowInsetTop + padding.top,
                bottom = insets.systemWindowInsetBottom + padding.bottom
            )
        }

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
        viewModel.openMainScreen.subscribe(viewLifecycleOwner) { router.openMainScreen(this) }
    }

    private fun getLoginAndPassword() = signUpLoginInput.text.toString() to signUpPasswordInput.text.toString()
}