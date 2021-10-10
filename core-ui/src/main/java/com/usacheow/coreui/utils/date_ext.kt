package com.usacheow.coreui.utils

import com.usacheow.coreui.R as CoreUiR
import com.usacheow.core.resource.ResourcesWrapper
import java.time.LocalDate
import java.time.LocalDateTime

fun LocalDateTime.getMonthName(resourcesWrapper: ResourcesWrapper) = toLocalDate().getMonthName(resourcesWrapper)

fun LocalDate.getMonthName(resourcesWrapper: ResourcesWrapper): String {
    return resourcesWrapper.getStringArray(CoreUiR.array.month_names)[month.value - 1]
}