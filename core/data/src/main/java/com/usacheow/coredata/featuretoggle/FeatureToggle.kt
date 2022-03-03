package com.usacheow.coredata.featuretoggle

interface FeatureToggle {

    fun isEnabled(feature: Feature): Boolean

    fun isRemoteEnabled(feature: Feature): Boolean

    fun isLocalEnabled(feature: Feature): Boolean?
}