package com.usacheow.coreui.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/*  Single permission request for Fragment

    val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        when {
            it -> onGrantedAction()
            else -> onDeniedAction()
        }
    }

    permissionRequest.launch(REQUIRED_PERMISSION)
*/

fun Context.arePermissionsGranted(permissions: Array<String>) = permissions.all {
    ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

fun Context.isPermissionGranted(permission: String) = ContextCompat
    .checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED