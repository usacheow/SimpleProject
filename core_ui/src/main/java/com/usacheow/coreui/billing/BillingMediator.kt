package com.usacheow.coreui.billing

import android.app.Activity

interface BillingMediator {

    fun openBillingScreen(product: Product, activity: Activity)
}