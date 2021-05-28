package com.usacheow.coredata.coroutine

import kotlinx.coroutines.CoroutineScope

interface ApplicationCoroutineScopeHolder {

    val applicationScope: CoroutineScope
}