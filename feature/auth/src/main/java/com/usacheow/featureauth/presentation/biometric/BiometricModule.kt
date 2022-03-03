package com.usacheow.featureauth.presentation.biometric

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface BiometricModule {

    @Binds
    fun biometricCreateManager(manager: BiometricCreateManagerImpl): BiometricCreateManager

    @Binds
    fun biometricEnterManager(manager: BiometricEnterManagerImpl): BiometricEnterManager
}