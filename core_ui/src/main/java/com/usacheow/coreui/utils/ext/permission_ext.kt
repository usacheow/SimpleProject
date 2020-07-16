package com.usacheow.coreui.utils.ext

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.arePermissionsGranted(permissions: Array<String>) = permissions.all {
    ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}