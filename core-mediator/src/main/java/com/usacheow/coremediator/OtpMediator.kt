package com.usacheow.coremediator

import com.usacheow.core.navigation.FeatureNavDirection

interface OtpMediator {

    fun getOtpFlowDirection(codeLength: Int): FeatureNavDirection
}