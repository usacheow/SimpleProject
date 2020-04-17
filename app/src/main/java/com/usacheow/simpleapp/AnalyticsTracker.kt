package com.usacheow.simpleapp

import android.app.Application
import androidx.core.os.bundleOf
import com.usacheow.core.analytics.Events
import com.usacheow.core.analytics.ITracker
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import javax.inject.Inject

class AnalyticsTracker
@Inject constructor(
    private val application: Application
) : ITracker {

    //todo: add firebase config json
//    private val firebaseAnalytics by lazy {
//        FirebaseAnalytics.getInstance(application)
//    }

    init {
        YandexMetricaConfig.newConfigBuilder(BuildConfig.APP_METRICA_KEY).build().apply {
            YandexMetrica.activate(application, this)
            YandexMetrica.enableActivityAutoTracking(application)
        }
    }

    override fun trackEvent(event: Events, attributes: Map<String, String>) {
        val bundle = bundleOf(*attributes.map { it.key to it.value }.toTypedArray())

//        firebaseAnalytics.logEvent(event.value, bundle)
    }
}