package com.usacheow.coreui

import com.usacheow.coreui.helper.biometric.BiometricCreateManager
import com.usacheow.coreui.helper.biometric.BiometricCreateManagerImpl
import com.usacheow.coreui.helper.biometric.BiometricEnterManager
import com.usacheow.coreui.helper.biometric.BiometricEnterManagerImpl
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