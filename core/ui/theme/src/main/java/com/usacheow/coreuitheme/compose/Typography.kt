package com.usacheow.coreuitheme.compose

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

internal val LocalSpecificTypography = staticCompositionLocalOf { DefaultSpecificTypography }
internal val DefaultTypography by lazy { DefaultSpecificTypography.toTypography() }

internal val DefaultSpecificTypography = SpecificTypography(
    displayLarge = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 52.sp,
        letterSpacing = 0.em,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 44.sp,
        letterSpacing = 0.em,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 36.sp,
        letterSpacing = 0.em,
    ),

    headlineLarge = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.em,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.em,
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.009.em,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.007.em,
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.009.em,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.0178.em,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.03.em,
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.007.em,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0416.em,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 11.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.045.em,
    ),
)

data class SpecificTypography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
) {

    fun toTypography() = Typography(
        displayLarge = displayLarge,
        displayMedium = displayMedium,
        displaySmall = displaySmall,
        headlineLarge = headlineLarge,
        headlineMedium = headlineMedium,
        headlineSmall = headlineSmall,
        titleLarge = titleLarge,
        titleMedium = titleMedium,
        titleSmall = titleSmall,
        bodyLarge = bodyLarge,
        bodyMedium = bodyMedium,
        bodySmall = bodySmall,
        labelLarge = labelLarge,
        labelMedium = labelMedium,
        labelSmall = labelSmall,
    )
}