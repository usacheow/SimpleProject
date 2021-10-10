package com.usacheow.coreui.utils

import com.usacheow.coreui.R
import com.usacheow.core.resource.ResourcesWrapper
import java.time.LocalDate
import java.time.LocalDateTime

fun LocalDateTime.getMonthName(resourcesWrapper: ResourcesWrapper) = toLocalDate().getMonthName(resourcesWrapper)

fun LocalDate.getMonthName(resourcesWrapper: ResourcesWrapper): String {
    return resourcesWrapper.getStringArray(R.array.month_names)[month.value - 1]
}