package com.usacheow.coreui

import com.usacheow.coreui.utils.biometric.BiometricCreateManager
import com.usacheow.coreui.utils.biometric.BiometricCreateManagerImpl
import com.usacheow.coreui.utils.biometric.BiometricEnterManager
import com.usacheow.coreui.utils.biometric.BiometricEnterManagerImpl
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