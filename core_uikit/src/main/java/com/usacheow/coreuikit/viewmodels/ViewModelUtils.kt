package com.usacheow.coreuikit.viewmodels

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(
    noinline ownerInitializer: () -> ViewModelStoreOwner = { this },
    noinline factoryInitializer: () -> ViewModelProvider.Factory
) = lazy { ViewModelProvider(ownerInitializer(), factoryInitializer())[T::class.java] }

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    noinline ownerInitializer: () -> ViewModelStoreOwner = { this },
    noinline factoryInitializer: () -> ViewModelProvider.Factory
) = lazy { ViewModelProvider(ownerInitializer(), factoryInitializer())[T::class.java] }