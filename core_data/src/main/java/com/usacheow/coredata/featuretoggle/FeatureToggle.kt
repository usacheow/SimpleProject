package com.usacheow.coredata.featuretoggle

interface FeatureToggle {

    fun isEnabled(feature: Feature): Boolean

    fun enable(features: List<Feature>)

    fun enable(feature: Feature)

    fun disable(feature: Feature)

    fun clear(feature: Feature)

    fun clear()
}