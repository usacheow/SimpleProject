package com.usacheow.authorization.di

import com.usacheow.authorization.presentation.fragment.PinCodeFragment
import com.usacheow.authorization.presentation.fragment.SignInByPhoneFragment
import com.usacheow.authorization.presentation.fragment.SignInFragment
import com.usacheow.authorization.presentation.fragment.SignUpFragment
import com.usacheow.authorization.presentation.fragment.SmsCodeModalFragment
import com.usacheow.di.FragmentScope
import com.usacheow.diprovider.DiProvider
import dagger.Component

@FragmentScope
@Component(
    dependencies = [DiProvider::class],
    modules = [PresentationModule::class, DataModule::class]
)
interface AuthorizationComponent {

    companion object {

        fun init(diProvider: DiProvider): AuthorizationComponent {
            return DaggerAuthorizationComponent.builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(fragment: SignInByPhoneFragment)

    fun inject(fragment: SignUpFragment)

    fun inject(fragment: SignInFragment)

    fun inject(fragment: PinCodeFragment)

    fun inject(fragment: SmsCodeModalFragment)
}