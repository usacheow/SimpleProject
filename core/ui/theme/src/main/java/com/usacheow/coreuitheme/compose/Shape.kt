package com.usacheow.coreuitheme.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

internal val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(AppTheme.specificDimens.radius_extra_small),
    small = RoundedCornerShape(AppTheme.specificDimens.radius_small),
    medium = RoundedCornerShape(AppTheme.specificDimens.radius_medium),
    large = RoundedCornerShape(AppTheme.specificDimens.radius_large),
    extraLarge = RoundedCornerShape(AppTheme.specificDimens.radius_extra_large),
)