package com.usacheow.simpleapp

import android.app.Application
import com.usacheow.corecommon.analytics.AnalyticsTracker
import com.usacheow.corecommon.analytics.AnalyticsTrackerHolder
import com.usacheow.coredata.coroutine.ApplicationCoroutineScopeHolder
import com.usacheow.coredata.featuretoggle.FeatureToggleUpdater
import com.usacheow.coredata.source.NetworkStateSource
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class SimpleApp : Application(), ApplicationCoroutineScopeHolder {

    @Inject lateinit var tracker: AnalyticsTracker

    @Inject lateinit var featureToggleUpdater: FeatureToggleUpdater

    @Inject lateinit var networkStateSource: NetworkStateSource

    override val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        AnalyticsTrackerHolder.init(tracker)
    }
}