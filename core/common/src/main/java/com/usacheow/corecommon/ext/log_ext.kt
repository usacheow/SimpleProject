package com.usacheow.corecommon.ext

import android.util.Log

fun log(value: String) {
    Log.d("simple_logger", value)
}

fun logError(value: String) {
    Log.e("simple_logger", value)
}