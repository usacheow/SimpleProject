package com.usacheow.featureauth.di

import androidx.lifecycle.ViewModel
import com.usacheow.app_shared.otp.SmsCodeViewModel
import com.usacheow.coreui.viewmodels.ViewModelFactoryModule
import com.usacheow.coreui.viewmodels.ViewModelKey
import com.usacheow.di.FragmentScope
import com.usacheow.featureauth.presentation.viewmodels.PinCodeViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInWithLoginAndPasswordViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignInWithPhoneViewModel
import com.usacheow.featureauth.presentation.viewmodels.SignUpWithLoginAndPasswordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface PresentationModule {

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(PinCodeViewModel::class)
    fun pinCodeViewModel(viewModel: PinCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(SignUpWithLoginAndPasswordViewModel::class)
    fun signUpViewModel(viewModel: SignUpWithLoginAndPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(SignInWithLoginAndPasswordViewModel::class)
    fun signInViewModel(viewModel: SignInWithLoginAndPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(SignInWithPhoneViewModel::class)
    fun signInByPhoneViewModel(viewModel: SignInWithPhoneViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(SmsCodeViewModel::class)
    fun smsCodeViewModel(viewModel: SmsCodeViewModel): ViewModel
}