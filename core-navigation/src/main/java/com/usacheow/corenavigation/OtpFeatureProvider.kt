package com.usacheow.corenavigation

import android.os.Parcelable
import com.usacheow.core.navigation.FeatureNavDirection
import kotlinx.parcelize.Parcelize

interface OtpFeatureProvider {

    fun getOtpFlowDirection(args: OtpArgs): FeatureNavDirection

    @Parcelize
    data class OtpArgs(
        val codeLength: Int,
    ) : Parcelable
}