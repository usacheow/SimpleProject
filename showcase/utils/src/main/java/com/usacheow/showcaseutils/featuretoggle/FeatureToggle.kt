package com.usacheow.showcaseutils.featuretoggle

import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.bindSingleton
import org.kodein.di.delegate
import org.kodein.di.instance

interface FeatureToggle {

    fun isEnabled(feature: Feature): Boolean

    fun isRemoteEnabled(feature: Feature): Boolean

    fun isLocalEnabled(feature: Feature): Boolean?
}

interface EditableFeatureToggle : FeatureToggle {

    fun setRemoteValue(feature: Feature, value: Boolean)

    fun setLocalValue(feature: Feature, value: Boolean?)

    fun clearRemoteValues()
}

val featureToggleDiModule by DI.Module {
    bindSingleton { LocalFeatureToggleStorage(instance()) }
    bindSingleton { RemoteFeatureToggleStorage(instance()) }
    bindSingleton<EditableFeatureToggle> { FeatureToggleImpl(instance(), instance(), instance()) }
    delegate<FeatureToggle>().to<EditableFeatureToggle>()
    bindSingleton { FeatureToggleUpdater(instance()) }
}