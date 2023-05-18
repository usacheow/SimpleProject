package com.usacheow.showcaseutils.biometric

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val biometricDiModule by DI.Module {
    bindSingleton { BiometricManagerWrapper(instance()) }
    bindSingleton { BiometricPromptWrapper(instance()) }
}