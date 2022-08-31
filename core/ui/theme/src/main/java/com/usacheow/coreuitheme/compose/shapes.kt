package com.usacheow.coreuitheme.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

internal val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(AppTheme.specificValues.radius_extra_small),
    small = RoundedCornerShape(AppTheme.specificValues.radius_small),
    medium = RoundedCornerShape(AppTheme.specificValues.radius_medium),
    large = RoundedCornerShape(AppTheme.specificValues.radius_large),
    extraLarge = RoundedCornerShape(AppTheme.specificValues.radius_extra_large),
)