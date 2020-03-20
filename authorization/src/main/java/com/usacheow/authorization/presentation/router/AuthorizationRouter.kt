package com.usacheow.authorization.presentation.router

import com.usacheow.authorization.presentation.fragment.SignUpFragment
import com.usacheow.authorization.presentation.fragment.SmsCodeModalFragment
import com.usacheow.coreuikit.base.Router
import com.usacheow.coreuikit.fragments.SimpleFragment
import javax.inject.Inject

class AuthorizationRouter
@Inject constructor() : Router {

    fun openMainScreen(fragment: SimpleFragment) {
    }

    fun openSignUpScreen(fragment: SimpleFragment) {
        fragment.getContainer {
            show(SignUpFragment.newInstance())
        }
    }

    fun openConfirmScreen(fragment: SimpleFragment, codeLength: Int) {
        SmsCodeModalFragment.newInstance(codeLength)
            .show(fragment.childFragmentManager, SmsCodeModalFragment::javaClass.name)
    }
}