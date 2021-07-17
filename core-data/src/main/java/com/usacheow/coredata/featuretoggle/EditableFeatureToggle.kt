package com.usacheow.coredata.featuretoggle

interface EditableFeatureToggle : FeatureToggle {

    fun enable(features: List<Feature>)

    fun enable(feature: Feature)

    fun disable(feature: Feature)

    fun clear(feature: Feature)

    fun clear()
}