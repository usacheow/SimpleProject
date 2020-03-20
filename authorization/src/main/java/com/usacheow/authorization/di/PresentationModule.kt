package com.usacheow.authorization.di

import androidx.lifecycle.ViewModel
import com.usacheow.authorization.presentation.viewmodels.PinCodeViewModel
import com.usacheow.authorization.presentation.viewmodels.SignInByPhoneViewModel
import com.usacheow.authorization.presentation.viewmodels.SignInViewModel
import com.usacheow.authorization.presentation.viewmodels.SignUpViewModel
import com.usacheow.authorization.presentation.viewmodels.SmsCodeViewModel
import com.usacheow.coreuikit.viewmodels.ViewModelFactoryModule
import com.usacheow.coreuikit.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(PinCodeViewModel::class)
    fun pinCodeViewModel(viewModel: PinCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun signInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInByPhoneViewModel::class)
    fun signInByPhoneViewModel(viewModel: SignInByPhoneViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SmsCodeViewModel::class)
    fun smsCodeViewModel(viewModel: SmsCodeViewModel): ViewModel
}