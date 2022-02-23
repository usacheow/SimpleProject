package com.usacheow.coredata.featuretoggle

interface EditableFeatureToggle : FeatureToggle {

    fun setRemoteValue(feature: Feature, value: Boolean)

    fun setLocalValue(feature: Feature, value: Boolean?)

    fun clearRemoteValues()
}