package com.usacheow.apptest

import android.app.Application
import com.usacheow.coreui.app.ApplicationCoroutineScopeHolder
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class TestApp : Application(), ApplicationCoroutineScopeHolder {

    override val applicationScope = CoroutineScope(SupervisorJob())
}