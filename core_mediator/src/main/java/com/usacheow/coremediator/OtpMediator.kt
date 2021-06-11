package com.usacheow.coremediator

import androidx.navigation.NavDirections

interface OtpMediator {

    fun getOtpFlowDirection(codeLength: Int): NavDirections
}