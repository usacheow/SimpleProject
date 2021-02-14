package com.usacheow.coreui.base

import androidx.core.view.WindowInsetsCompat
import com.usacheow.coreui.utils.view.PaddingValue

interface ApplyWindowInsets {

    fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) = Unit
}