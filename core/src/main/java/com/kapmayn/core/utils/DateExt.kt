package com.kapmayn.core.utils

import java.text.SimpleDateFormat
import java.util.*

val DD = "dd"

val MM_SS = "mm:ss"

val HH_MM = "HH:mm"

val HH_MM_DD_MMMM_YYYY = "HH:mm dd MMMM yyyy"

fun Long.getDay(): Int {
    val dateFormat = SimpleDateFormat(DD, Locale.getDefault())
    val date = dateFormat.format(this)
    return Integer.parseInt(date)
}

fun Long.getFullDate(): String {
    val dateFormat = SimpleDateFormat(HH_MM_DD_MMMM_YYYY, Locale.getDefault())
    return dateFormat.format(this)
}

fun Long.getTime(): String {
    val dateFormat = SimpleDateFormat(HH_MM, Locale.getDefault())
    return dateFormat.format(this)
}

fun Long.getDate(): String {
    return when (getDay()) {
        System.currentTimeMillis().getDay() -> getTime()
        else -> getFullDate()
    }
}

fun Long.getDuration(): String {
    val dateFormat = SimpleDateFormat(MM_SS, Locale.getDefault())
    return dateFormat.format(this)
}