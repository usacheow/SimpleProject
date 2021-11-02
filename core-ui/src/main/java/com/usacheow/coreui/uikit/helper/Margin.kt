package com.usacheow.coreui.uikit.helper

import android.view.View
import android.view.ViewGroup

sealed class Margin

data class Margin2(
    val vertical: Int? = null,
    val horizontal: Int? = null,
) : Margin()

data class MarginVertical(
    val top: Int? = null,
    val bottom: Int? = null,
) : Margin()

data class MarginHorizontal(
    val left: Int? = null,
    val right: Int? = null,
) : Margin()

data class Margin4(
    val top: Int? = null,
    val bottom: Int? = null,
    val left: Int? = null,
    val right: Int? = null,
) : Margin()

data class MarginTop(
    val top: Int? = null,
) : Margin()

data class MarginBottom(
    val bottom: Int? = null,
) : Margin()

data class MarginLeft(
    val left: Int? = null,
) : Margin()

data class MarginRight(
    val right: Int? = null,
) : Margin()

fun View.updateMargins(margin: Margin) {
    val (leftPx, rightPx) = when (margin) {
        is Margin2 -> margin.horizontal to margin.horizontal
        is Margin4 -> margin.left to margin.right

        is MarginVertical -> null to null
        is MarginHorizontal -> margin.left to margin.right

        is MarginTop -> null to null
        is MarginBottom -> null to null
        is MarginLeft -> margin.left to null
        is MarginRight -> null to margin.right
    }
    val (topPx, bottomPx) = when (margin) {
        is Margin2 -> margin.vertical to margin.vertical
        is Margin4 -> margin.top to margin.bottom

        is MarginVertical -> margin.top to margin.bottom
        is MarginHorizontal -> null to null

        is MarginTop -> margin.top to null
        is MarginBottom -> null to margin.bottom
        is MarginLeft -> null to null
        is MarginRight -> null to null
    }

    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(
            leftPx ?: leftMargin,
            topPx ?: topMargin,
            rightPx ?: rightMargin,
            bottomPx ?: bottomMargin
        )
    }
}