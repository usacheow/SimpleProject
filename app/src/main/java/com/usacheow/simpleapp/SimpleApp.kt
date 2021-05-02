package com.usacheow.simpleapp

import android.app.Application
import com.usacheow.appstate.provider.NetworkStateProvider
import com.usacheow.appstate.provider.PurchaseStateProvider
import com.usacheow.coredata.featuretoggle.FeatureToggleUpdater
import com.usacheow.coreui.analytics.AnalyticsTrackerHolder
import com.usacheow.coreui.analytics.Tracker
import com.usacheow.coreui.app.ApplicationCoroutineScopeHolder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
class SimpleApp : Application(), ApplicationCoroutineScopeHolder {

    //move to another place (e.g. start activity)
    //when appmetrica is enabled
    //https://github.com/yandexmobile/metrica-sdk-android/issues/76
    @Inject lateinit var tracker: Tracker

    @Inject lateinit var featureToggleUpdater: FeatureToggleUpdater

    @Inject lateinit var purchaseStateProvider: PurchaseStateProvider

    @Inject lateinit var networkStateProvider: NetworkStateProvider

    override val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        AnalyticsTrackerHolder.init(tracker)
    }
}