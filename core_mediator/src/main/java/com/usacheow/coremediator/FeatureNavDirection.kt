package com.usacheow.coremediator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections

data class FeatureNavDirection(
    @IdRes override val actionId: Int,
    override val arguments: Bundle = Bundle(),
) : NavDirections