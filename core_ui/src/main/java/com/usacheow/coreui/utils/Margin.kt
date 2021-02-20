package com.usacheow.coreui.utils

import android.view.View
import android.view.ViewGroup

sealed class Margin

data class Margin2(
    val verticalPx: Int? = null,
    val horizontalPx: Int? = null,
) : Margin()

data class MarginVertical(
    val topPx: Int? = null,
    val bottomPx: Int? = null,
) : Margin()

data class MarginHorizontal(
    val leftPx: Int? = null,
    val rightPx: Int? = null,
) : Margin()

data class Margin4(
    val topPx: Int? = null,
    val bottomPx: Int? = null,
    val leftPx: Int? = null,
    val rightPx: Int? = null,
) : Margin()

data class MarginTop(
    val topPx: Int? = null,
) : Margin()

data class MarginBottom(
    val bottomPx: Int? = null,
) : Margin()

data class MarginLeft(
    val leftPx: Int? = null,
) : Margin()

data class MarginRight(
    val rightPx: Int? = null,
) : Margin()

fun View.updateMargins(margin: Margin) {
    val (leftPx, rightPx) = when (margin) {
        is Margin2 -> margin.horizontalPx to margin.horizontalPx
        is Margin4 -> margin.leftPx to margin.rightPx

        is MarginVertical -> null to null
        is MarginHorizontal -> margin.leftPx to margin.rightPx

        is MarginTop -> null to null
        is MarginBottom -> null to null
        is MarginLeft -> margin.leftPx to null
        is MarginRight -> null to margin.rightPx
    }
    val (topPx, bottomPx) = when (margin) {
        is Margin2 -> margin.verticalPx to margin.verticalPx
        is Margin4 -> margin.topPx to margin.bottomPx

        is MarginVertical -> margin.topPx to margin.bottomPx
        is MarginHorizontal -> null to null

        is MarginTop -> margin.topPx to null
        is MarginBottom -> null to margin.bottomPx
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