package com.usacheow.coredata.featuretoggle

interface FeatureToggle {

    fun isEnabled(feature: Feature): Boolean
}