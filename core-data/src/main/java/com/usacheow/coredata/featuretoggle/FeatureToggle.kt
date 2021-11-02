package com.usacheow.coredata.featuretoggle

interface FeatureToggle {

    fun isEnabled(feature: Feature): Boolean

    fun isRemoteEnabled(feature: Feature): Boolean

    fun isManualEnabled(feature: Feature): Boolean?
}