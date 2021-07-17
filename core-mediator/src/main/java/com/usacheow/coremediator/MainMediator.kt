package com.usacheow.coremediator

import androidx.navigation.NavDirections

interface MainMediator {

    fun getAFlowDirection(): NavDirections

    fun getMockFlowDirection(): NavDirections
}