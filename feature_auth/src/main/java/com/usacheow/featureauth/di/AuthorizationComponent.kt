package com.usacheow.featureauth.di

import com.usacheow.app_shared.otp.SmsCodeModalFragment
import com.usacheow.di.DiProvider
import com.usacheow.di.FragmentScope
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureauth.presentation.fragment.SignInFragment
import com.usacheow.featureauth.presentation.fragment.SignInWithPhoneFragment
import com.usacheow.featureauth.presentation.fragment.SignUpFragment
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

    fun inject(fragment: SignInWithPhoneFragment)

    fun inject(fragment: SignUpFragment)

    fun inject(fragment: SignInFragment)

    fun inject(fragment: PinCodeFragment)

    fun inject(fragment: SmsCodeModalFragment)
}