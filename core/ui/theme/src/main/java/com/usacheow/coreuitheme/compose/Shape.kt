package com.usacheow.coreuitheme.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

internal val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(Dimen.radius_small),
    small = RoundedCornerShape(Dimen.radius_small),
    medium = RoundedCornerShape(Dimen.radius_medium),
    large = RoundedCornerShape(Dimen.radius_large),
    extraLarge = RoundedCornerShape(Dimen.radius_large),
)

val CircleShape = RoundedCornerShape(percent = 50)