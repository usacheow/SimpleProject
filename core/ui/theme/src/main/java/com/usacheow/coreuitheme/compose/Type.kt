package com.usacheow.coreuitheme.compose

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.usacheow.coreuitheme.R as CoreUiThemeR

const val secondaryTextAlpha = 0.6f

private val FontFamilyW100 = FontFamily(
    listOf(
        Font(CoreUiThemeR.font.thin, weight = FontWeight.W100, style = FontStyle.Normal),
        Font(CoreUiThemeR.font.thin_italic, weight = FontWeight.W100, style = FontStyle.Italic),
    )
)
private val FontFamilyW200 = FontFamily(
    listOf(
        Font(CoreUiThemeR.font.light, weight = FontWeight.W200, style = FontStyle.Normal),
        Font(CoreUiThemeR.font.light_italic, weight = FontWeight.W200, style = FontStyle.Italic),
    )
)
private val FontFamilyW400 = FontFamily(
    listOf(
        Font(CoreUiThemeR.font.regular, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(CoreUiThemeR.font.regular_italic, weight = FontWeight.W400, style = FontStyle.Italic),
    )
)
private val FontFamilyW500 = FontFamily(
    listOf(
        Font(CoreUiThemeR.font.medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(CoreUiThemeR.font.medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
    )
)
private val FontFamilyW700 = FontFamily(
    listOf(
        Font(CoreUiThemeR.font.bold, weight = FontWeight.W700, style = FontStyle.Normal),
        Font(CoreUiThemeR.font.bold_italic, weight = FontWeight.W700, style = FontStyle.Italic),
    )
)

internal val AppTypography = Typography(
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
        letterSpacing = 0.em,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 28.sp,
        letterSpacing = 0.em,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 24.sp,
        letterSpacing = 0.em,
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 22.sp,
        letterSpacing = 0.em,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 16.sp,
        letterSpacing = 0.009.em,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 14.sp,
        letterSpacing = 0.007.em,
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 16.sp,
        letterSpacing = 0.009.em,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 14.sp,
        letterSpacing = 0.0178.em,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamilyW400,
        fontSize = 12.sp,
        letterSpacing = 0.03.em,
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 14.sp,
        letterSpacing = 0.007.em,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 12.sp,
        letterSpacing = 0.0416.em,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamilyW500,
        fontSize = 11.sp,
        letterSpacing = 0.045.em,
    ),
)