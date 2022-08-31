package com.usacheow.coreuitheme.compose

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.usacheow.coreuitheme.R

internal val LocalSpecificTypography = staticCompositionLocalOf { DefaultSpecificTypography }
internal val DefaultTypography by lazy { DefaultSpecificTypography.toTypography() }

internal val FontFamilyW200 = FontFamily(
    listOf(
        Font(R.font.light, weight = FontWeight.W200, style = FontStyle.Normal),
        Font(R.font.light_italic, weight = FontWeight.W200, style = FontStyle.Italic),
    )
)
internal val FontFamilyW400 = FontFamily(
    listOf(
        Font(R.font.regular, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(R.font.regular_italic, weight = FontWeight.W400, style = FontStyle.Italic),
    )
)
internal val FontFamilyW500 = FontFamily(
    listOf(
        Font(R.font.medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(R.font.medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
    )
)
internal val FontFamilyW600 = FontFamily(
    listOf(
        Font(R.font.medium, weight = FontWeight.W600, style = FontStyle.Normal),
        Font(R.font.medium_italic, weight = FontWeight.W600, style = FontStyle.Italic),
    )
)
internal val FontFamilyW700 = FontFamily(
    listOf(
        Font(R.font.bold, weight = FontWeight.W700, style = FontStyle.Normal),
        Font(R.font.bold_italic, weight = FontWeight.W700, style = FontStyle.Italic),
    )
)
internal val FontFamilyW900 = FontFamily(
    listOf(
        Font(R.font.bold, weight = FontWeight.W900, style = FontStyle.Normal),
        Font(R.font.bold_italic, weight = FontWeight.W900, style = FontStyle.Italic),
    )
)

internal val DefaultSpecificTypography = SpecificTypography(
    displayLarge = TextStyle(
        fontFamily = FontFamilyW900,
        fontSize = 52.sp,
        letterSpacing = 0.em,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamilyW900,
        fontSize = 44.sp,
        letterSpacing = 0.em,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamilyW900,
        fontSize = 36.sp,
        letterSpacing = 0.em,
    ),

    headlineLarge = TextStyle(
        fontFamily = FontFamilyW700,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.em,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamilyW700,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamilyW700,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.em,
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamilyW600,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamilyW600,
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
        fontFamily = FontFamilyW400,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0416.em,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamilyW200,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0416.em,
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
)