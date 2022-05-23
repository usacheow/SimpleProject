package com.usacheow.coreui.tools

import android.content.ClipData
import android.content.ClipboardManager

fun ClipboardManager.primaryText() = primaryClip?.getItemAt(0)?.text?.toString()

fun ClipboardManager.primaryText(value: String, label: String = "") {
    setPrimaryClip(ClipData.newPlainText(label, value))
}