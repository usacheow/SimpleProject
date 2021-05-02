package com.usacheow.appdemo

import android.app.Application
import com.usacheow.coreui.app.ApplicationCoroutineScopeHolder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class DemoApp : Application(), ApplicationCoroutineScopeHolder {

    override val applicationScope = CoroutineScope(SupervisorJob())
}