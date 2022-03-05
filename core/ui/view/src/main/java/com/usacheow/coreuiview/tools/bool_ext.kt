package com.usacheow.coreuiview.tools

inline fun <reified T> T.ifFalse(value: Boolean) = when {
    !value -> this
    else -> null
}