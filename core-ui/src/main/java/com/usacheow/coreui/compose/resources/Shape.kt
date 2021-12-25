package com.usacheow.coreui.compose.resources

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes

val Shapes = Shapes(
    small = RoundedCornerShape(Dimen.radius_small),
    medium = RoundedCornerShape(Dimen.radius_medium),
    large = RoundedCornerShape(Dimen.radius_large),
)

val CircleShape = RoundedCornerShape(percent = 50)