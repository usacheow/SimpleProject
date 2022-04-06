package com.usacheow.coreuitheme.compose

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

const val secondaryTextAlpha = 0.6f

internal val AppTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        letterSpacing = 0.em,
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        letterSpacing = 0.em,
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        letterSpacing = 0.em,
    ),

    headlineLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        letterSpacing = 0.em,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        letterSpacing = 0.em,
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        letterSpacing = 0.em,
    ),

    titleLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        letterSpacing = 0.em,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.009.em,
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.007.em,
    ),

    bodyLarge = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.009.em,
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.0178.em,
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        letterSpacing = 0.03.em,
    ),

    labelLarge = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.007.em,
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        letterSpacing = 0.0416.em,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 11.sp,
        letterSpacing = 0.045.em,
    ),
)