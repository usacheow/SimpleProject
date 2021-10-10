package com.usacheow.coremediator

import android.os.Parcelable
import com.usacheow.core.navigation.FeatureNavDirection
import kotlinx.parcelize.Parcelize

interface OtpMediator {

    fun getOtpFlowDirection(args: OtpArgs): FeatureNavDirection

    @Parcelize
    data class OtpArgs(
        val codeLength: Int,
    ) : Parcelable
}