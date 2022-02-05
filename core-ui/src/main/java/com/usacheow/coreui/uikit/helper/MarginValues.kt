package com.usacheow.coreui.uikit.helper

import android.view.View
import android.view.ViewGroup

sealed class MarginValues {

    data class One(
        val value: Int? = null,
    ) : MarginValues()

    data class Acis(
        val vertical: Int? = null,
        val horizontal: Int? = null,
    ) : MarginValues()

    data class Vertical(
        val top: Int? = null,
        val bottom: Int? = null,
    ) : MarginValues()

    data class Horizontal(
        val start: Int? = null,
        val end: Int? = null,
    ) : MarginValues()

    data class All(
        val top: Int? = null,
        val bottom: Int? = null,
        val start: Int? = null,
        val end: Int? = null,
    ) : MarginValues()

    data class Top(
        val top: Int? = null,
    ) : MarginValues()

    data class Bottom(
        val bottom: Int? = null,
    ) : MarginValues()

    data class Start(
        val start: Int? = null,
    ) : MarginValues()

    data class End(
        val end: Int? = null,
    ) : MarginValues()
}

fun View.updateMargins(margin: MarginValues) {
    val (leftPx, rightPx) = when (margin) {
        is MarginValues.One -> margin.value to margin.value

        is MarginValues.Acis -> margin.horizontal to margin.horizontal
        is MarginValues.All -> margin.start to margin.end

        is MarginValues.Vertical -> null to null
        is MarginValues.Horizontal -> margin.start to margin.end

        is MarginValues.Top -> null to null
        is MarginValues.Bottom -> null to null
        is MarginValues.Start -> margin.start to null
        is MarginValues.End -> null to margin.end
    }
    val (topPx, bottomPx) = when (margin) {
        is MarginValues.One -> margin.value to margin.value

        is MarginValues.Acis -> margin.vertical to margin.vertical
        is MarginValues.All -> margin.top to margin.bottom

        is MarginValues.Vertical -> margin.top to margin.bottom
        is MarginValues.Horizontal -> null to null

        is MarginValues.Top -> margin.top to null
        is MarginValues.Bottom -> null to margin.bottom
        is MarginValues.Start -> null to null
        is MarginValues.End -> null to null
    }

    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(
            leftPx ?: leftMargin,
            topPx ?: topMargin,
            rightPx ?: rightMargin,
            bottomPx ?: bottomMargin,
        )
    }
}