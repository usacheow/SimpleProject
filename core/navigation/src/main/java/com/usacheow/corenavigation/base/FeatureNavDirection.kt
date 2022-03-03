package com.usacheow.corecommon.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureNavDirection(
    @IdRes override val actionId: Int,
    override val arguments: Bundle = Bundle(),

    val resetTo: ResetTo? = null,
) : NavDirections, Parcelable

@Parcelize
data class ResetTo(
    @IdRes val destinationId: Int,
    val inclusive: Boolean = true,
) : Parcelable

fun NavDirections.toFeatureNavDirection() = FeatureNavDirection(actionId, arguments)