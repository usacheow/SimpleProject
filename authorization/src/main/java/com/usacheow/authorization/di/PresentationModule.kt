package com.usacheow.authorization.di

import androidx.lifecycle.ViewModel
import com.usacheow.authorization.presentation.viewmodels.PinCodeViewModel
import com.usacheow.authorization.presentation.viewmodels.SignInWithLoginAndPasswordViewModel
import com.usacheow.authorization.presentation.viewmodels.SignInWithPhoneViewModel
import com.usacheow.authorization.presentation.viewmodels.SignUpWithLoginAndPasswordViewModel
import com.usacheow.authorization.presentation.viewmodels.SmsCodeViewModel
import com.usacheow.coreuikit.viewmodels.ViewModelFactoryModule
import com.usacheow.coreuikit.viewmodels.ViewModelKey
import com.usacheow.di.FragmentScope
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