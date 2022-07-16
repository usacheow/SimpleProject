package com.usacheow.simpleapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.usacheow.corecommon.analytics.AnalyticsTracker
import com.usacheow.corecommon.analytics.AnalyticsTrackerHolder
import com.usacheow.coredata.coroutine.ApplicationCoroutineScopeHolder
import com.usacheow.basesources.featuretoggle.FeatureToggleUpdater
import com.usacheow.basesources.NetworkStateSource
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import coil.decode.SvgDecoder
import com.usacheow.coredata.network.ApiConfig

@HiltAndroidApp
class SimpleApp : Application(), ApplicationCoroutineScopeHolder, ImageLoaderFactory {

    @Inject lateinit var tracker: AnalyticsTracker

    @Inject lateinit var featureToggleUpdater: FeatureToggleUpdater

    @Inject lateinit var networkStateSource: NetworkStateSource

    @Inject lateinit var apiConfig: ApiConfig

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        AnalyticsTrackerHolder.init(tracker)
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .okHttpClient {
            apiConfig.okHttpBuilder()
                .build()
        }
        .components {
            add(SvgDecoder.Factory())
        }
        .crossfade(true)
        .build()
}