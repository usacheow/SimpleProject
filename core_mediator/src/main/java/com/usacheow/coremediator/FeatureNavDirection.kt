package com.usacheow.coremediator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections

data class FeatureNavDirection(
    @IdRes private val actionId: Int,
    private val arguments: Bundle = Bundle(),
) : NavDirections {

    override fun getActionId() = actionId

    override fun getArguments() = arguments
}