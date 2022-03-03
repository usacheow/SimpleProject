package com.usacheow.coreui.screen.base

import androidx.core.view.WindowInsetsCompat
import com.usacheow.coreuiview.helper.PaddingValue

interface ApplyWindowInsets {

    fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat = insets
}