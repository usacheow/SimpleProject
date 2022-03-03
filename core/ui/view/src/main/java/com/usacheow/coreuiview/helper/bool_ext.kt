package com.usacheow.coreuiview.helper

inline fun <reified T> T.ifTrue(value: Boolean) = when {
    value -> this
    else -> null
}

inline fun <reified T> T.ifFalse(value: Boolean) = when {
    !value -> this
    else -> null
}