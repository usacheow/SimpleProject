package com.usacheow.coremediator

import androidx.navigation.NavDirections

interface OnBoardingMediator {

    fun getOnBoardingFlowDirection(nextScreenDirection: NavDirections): NavDirections
}