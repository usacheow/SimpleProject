package com.usacheow.coreui.app

import kotlinx.coroutines.CoroutineScope

interface ApplicationCoroutineScopeHolder {

    val applicationScope: CoroutineScope
}