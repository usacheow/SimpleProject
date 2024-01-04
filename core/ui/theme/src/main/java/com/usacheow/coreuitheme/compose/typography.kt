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

internal val DefaultFontFamily = FontFamily(
    listOf(
        Font(R.font.light, weight = FontWeight.W200, style = FontStyle.Normal),
        Font(R.font.light_italic, weight = FontWeight.W200, style = FontStyle.Italic),
        Font(R.font.regular, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(R.font.regular_italic, weight = FontWeight.W400, style = FontStyle.Italic),
        Font(R.font.medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(R.font.medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
        Font(R.font.medium, weight = FontWeight.W600, style = FontStyle.Normal),
        Font(R.font.medium_italic, weight = FontWeight.W600, style = FontStyle.Italic),
        Font(R.font.bold, weight = FontWeight.W700, style = FontStyle.Normal),
        Font(R.font.bold_italic, weight = FontWeight.W700, style = FontStyle.Italic),
        Font(R.font.bold, weight = FontWeight.W900, style = FontStyle.Normal),
        Font(R.font.bold_italic, weight = FontWeight.W900, style = FontStyle.Italic),
    )
)

internal val DefaultSpecificTypography = SpecificTypography(
    displayLarge = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em,
    ),

    headlineLarge = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em,
    ),
    headlineMedium = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 28.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.em,
    ),
    headlineSmall = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
    ),

    titleLarge = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
    ),
    titleMedium = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.009.em,
    ),
    titleSmall = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.007.em,
    ),

    bodyLarge = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.009.em,
    ),
    bodyMedium = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.0178.em,
    ),

    labelLarge = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.0416.em,
    ),
)

data class SpecificTypography(
    val displayLarge: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyLargeBold: TextStyle = bodyLarge.copy(fontWeight = FontWeight.W600),
    val bodyMedium: TextStyle,
    val bodyMediumBold: TextStyle = bodyMedium.copy(fontWeight = FontWeight.W600),
    val labelLarge: TextStyle,
    val labelLargeBold: TextStyle = labelLarge.copy(fontWeight = FontWeight.W600),
)