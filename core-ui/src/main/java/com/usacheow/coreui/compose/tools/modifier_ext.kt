package com.usacheow.coreui.compose.tools

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.margin(margin: Margin): Modifier {
    val (left, right) = when (margin) {
        is Margin.Acis -> margin.horizontal to margin.horizontal
        is Margin.All -> margin.start to margin.end

        is Margin.Vertical -> null to null
        is Margin.Horizontal -> margin.start to margin.end

        is Margin.Top -> null to null
        is Margin.Bottom -> null to null
        is Margin.Left -> margin.start to null
        is Margin.Right -> null to margin.end

        is Margin.Empty -> null to null
    }
    val (top, bottom) = when (margin) {
        is Margin.Acis -> margin.vertical to margin.vertical
        is Margin.All -> margin.top to margin.bottom

        is Margin.Vertical -> margin.top to margin.bottom
        is Margin.Horizontal -> null to null

        is Margin.Top -> margin.top to null
        is Margin.Bottom -> null to margin.bottom
        is Margin.Left -> null to null
        is Margin.Right -> null to null

        is Margin.Empty -> null to null
    }

    return padding(start = left ?: 0.dp, end = right ?: 0.dp, top = top ?: 0.dp, bottom = bottom ?: 0.dp)
}