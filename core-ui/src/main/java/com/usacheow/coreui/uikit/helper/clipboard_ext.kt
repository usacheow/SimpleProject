package com.usacheow.coreui.uikit.helper

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.fragment.app.Fragment

fun ClipboardManager.primaryText() = primaryClip?.getItemAt(0)?.text?.toString()

fun ClipboardManager.primaryText(value: String, label: String = "") {
    setPrimaryClip(ClipData.newPlainText(label, value))
}