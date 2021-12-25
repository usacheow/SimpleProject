package com.usacheow.coreui.values_ext

import com.usacheow.core.resource.ResourcesWrapper
import java.time.LocalDate
import java.time.LocalDateTime
import com.usacheow.coreui.R as CoreUiR

fun LocalDateTime.getMonthName(resourcesWrapper: ResourcesWrapper) = toLocalDate().getMonthName(resourcesWrapper)

fun LocalDate.getMonthName(resourcesWrapper: ResourcesWrapper): String {
    return resourcesWrapper.getStringArray(CoreUiR.array.month_names)[month.value - 1]
}