package com.usacheow.coredata.featuretoggle

import javax.inject.Inject

class FeatureToggleImpl
@Inject constructor(
    private val featureToggleStorage: FeatureToggleStorage
) : EditableFeatureToggle {

    override fun enable(features: List<Feature>) = save(Feature.values().toList(), features)

    private fun save(allFeatures: List<Feature>, enabledFeatures: List<Feature>) {
        val (enableFeatures, disableFeatures) = allFeatures.partition { it in enabledFeatures }
        enableFeatures.forEach { enable(it) }
        disableFeatures.forEach { disable(it) }
    }

    override fun isEnabled(feature: Feature) = featureToggleStorage.isEnabled(feature)

    override fun enable(feature: Feature) = featureToggleStorage.set(feature, true)

    override fun disable(feature: Feature) = featureToggleStorage.set(feature, false)

    override fun clear(feature: Feature) = featureToggleStorage.clear(feature)

    override fun clear() = Feature.values().toList().forEach { clear(it) }
}