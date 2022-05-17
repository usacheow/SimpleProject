package com.usacheow.coreuicompose.tools

import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

fun HapticFeedback.longPress() {
    performHapticFeedback(HapticFeedbackType.LongPress)
}