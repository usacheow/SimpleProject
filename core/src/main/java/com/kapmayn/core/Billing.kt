package com.kapmayn.core

import android.app.Activity

interface Billing {

    var onPayCompleteAction: () -> Unit

    fun init(activity: Activity)

    fun launchBilling(activity: Activity, skuId: String)
}